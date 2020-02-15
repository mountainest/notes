package logger

import (
	"bytes"
	"runtime/debug"
	"strconv"
	"strings"
	"time"

	"github.com/sirupsen/logrus"
)

type customFormatter struct {
	ForceCorlors  bool
	DisableColors bool
}

func (f *customFormatter) Format(entry *logrus.Entry) ([]byte, error) {
	// 时间
	str := getTimeStamp(entry.Time)
	// 日志级别
	str += " " + entry.Level.String()
	// 文件名、包名、函数名、行号
	str += " " + entry.Caller.File + "(" + entry.Caller.Function + ":" + strconv.Itoa(entry.Caller.Line) + ")\n"
	// 日志信息
	str += "-" + entry.Message + "\n"
	// 调用栈
	if entry.HasCaller() {
		str += getCallerStackInfo()
	}

	buffer := getOutAddr(entry.Buffer)
	buffer.WriteString(str)
	return buffer.Bytes(), nil
}

func getCallerStackInfo() string {
	str := string(debug.Stack())

	lastIdx := strings.LastIndex(str, "logrus")
	str = str[lastIdx:]
	firstIdx := strings.Index(str, "\n")
	str = str[firstIdx:]

	return str
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
