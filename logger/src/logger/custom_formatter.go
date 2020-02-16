package logger

import (
	"bytes"
	"fmt"
	"runtime"
	"strconv"
	"strings"
	"time"

	"github.com/sirupsen/logrus"
)

const (
	red    = 31
	yellow = 33
	blue   = 36
	gray   = 37
)

type customFormatter struct {
	ForceColors            bool
	DisableLevelTruncation bool
	isTerminal             bool
}

func (f *customFormatter) Format(entry *logrus.Entry) ([]byte, error) {
	// 时间
	str := getTimeStamp(entry.Time)
	// 日志级别
	logLevel := strings.ToUpper(entry.Level.String())
	if !f.DisableLevelTruncation {
		logLevel = logLevel[0:4]
	}
	str += " " + logLevel
	// 文件名、包名、函数名、行号
	str += " " + entry.Caller.File + ":" + strconv.Itoa(entry.Caller.Line) + "(" + entry.Caller.Function + ")\n"
	// 日志信息
	str += "-" + entry.Message + "\n"
	// 调用栈
	if entry.HasCaller() {
		str += getCallerStackInfo()
	}

	buffer := getOutAddr(entry.Buffer)
	if !f.isColored() {
		buffer.WriteString(str)
	} else {
		f.printColored(buffer, entry, str)
	}

	return buffer.Bytes(), nil
}

func getCallerStackInfo() string {
	pc := make([]uintptr, 10)
	num := runtime.Callers(10, pc)
	frames := runtime.CallersFrames(pc[:num])

	var str string
	var frame runtime.Frame
	isExisting := num > 0
	for isExisting {
		frame, isExisting = frames.Next()

		packageFunc := frame.Function
		if packageFunc == "runtime.main" || packageFunc == "runtime.goexit" {
			continue
		}

		str += "    " + frame.File + ":" + strconv.Itoa(frame.Line) + "(" + frame.Function + ")\n"
	}

	return str
}

func (f *customFormatter) printColored(b *bytes.Buffer, entry *logrus.Entry, str string) {
	var levelColor int
	switch entry.Level {
	case logrus.DebugLevel, logrus.TraceLevel:
		levelColor = gray
	case logrus.WarnLevel:
		levelColor = yellow
	case logrus.ErrorLevel, logrus.FatalLevel, logrus.PanicLevel:
		levelColor = red
	default:
		levelColor = blue
	}

	fmt.Fprintf(b, "\x1b[%dm%s\x1b[0m", levelColor, str)
}

func (f *customFormatter) init(entry *logrus.Entry) {
	if entry.Logger != nil {
		f.isTerminal = checkIfTerminal(entry.Logger.Out)
	}
}

func (f *customFormatter) isColored() bool {
	isColored := f.ForceColors || (f.isTerminal && (runtime.GOOS != "windows"))

	return isColored
}

func getOutAddr(entryBuffer *bytes.Buffer) *bytes.Buffer {
	buffer := entryBuffer

	if buffer == nil {
		buffer = &bytes.Buffer{}
	}

	if buffer.Len() > 0 {
		buffer.WriteByte('\n')
	}

	return buffer
}

func getTimeStamp(timeStamp time.Time) string {
	return timeStamp.Format("2006-01-02 15:04:05.000")
}
