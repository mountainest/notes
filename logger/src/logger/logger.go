package logger

import (
	"os"
	"time"

	rotatelogs "github.com/lestrrat-go/file-rotatelogs"
	"github.com/rifflock/lfshook"
	"github.com/sirupsen/logrus"
)

type loggerCfg struct {
	filePath     string
	fileBaseName string
	level        string
}

func new(filePath string, fileBaseName string, level string) loggerCfg {
	return loggerCfg{filePath, fileBaseName, level}
}

func InitLogger(filePath string, fileBaseName string, level string) {
	loggerCfg := new(filePath, fileBaseName, level)

	// 设置日志级别
	loggerCfg.initLoggerLevel()
	// 记录方法信息到Formatter里面的Caller
	logrus.SetReportCaller(true)
	// 命令行日志格式
	logrus.SetFormatter(&customFormatter{ForceColors: true})
	// 文件日志格式
	loggerCfg.initLogFile()
}

func (log *loggerCfg) initLogFile() {
	fileDir, err := log.getLogFileDir()
	if err != nil {
		return
	}

	fileName := fileDir + log.fileBaseName
	writerMaps := make(lfshook.WriterMap)
	for _, level := range logrus.AllLevels {
		writerMaps[level] = log.getPatternByLevel(fileName, level)
	}

	hook := lfshook.NewHook(writerMaps, &customFormatter{})
	logrus.AddHook(hook)

	return
}

func (log *loggerCfg) getPatternByLevel(fileName string, level logrus.Level) *rotatelogs.RotateLogs {
	fileName += "-" + level.String()

	maxAge := time.Hour * 24 * 7
	if level <= logrus.ErrorLevel {
		maxAge = time.Hour * 24 * 30
	}

	writer, err := rotatelogs.New(
		fileName+".%Y-%m-%d.log",
		rotatelogs.WithLinkName(fileName),         // 基于最新的日志建立软连接
		rotatelogs.WithRotationTime(time.Hour*24), // 按天切割日志
		rotatelogs.WithMaxAge(maxAge),
	)
	if err != nil {
		logrus.Fatal(err)
	}

	return writer
}

func (log *loggerCfg) getLogFileDir() (string, error) {
	_, err := os.Stat(log.filePath)
	if err == nil {
		return log.filePath, nil
	}

	if os.IsNotExist(err) {
		err = os.Mkdir(log.filePath, 755)
		if err == nil {
			return log.filePath, nil
		}
	}

	logrus.Fatal(err)

	return log.filePath, err
}

func (log *loggerCfg) initLoggerLevel() {
	level, err := logrus.ParseLevel(log.level)
	if err != nil {
		logrus.Fatal(err)
	}

	logrus.SetLevel(level)
}

func TestFunc() {
	logrus.Info("test")
}
