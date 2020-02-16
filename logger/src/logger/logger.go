package logger

import "github.com/sirupsen/logrus"

func InitLogger() {
	// 记录方法信息到Formatter里面的Caller
	logrus.SetReportCaller(true)
	// 命令行日志格式
	logrus.SetFormatter(&customFormatter{})
	// 文件日志格式
	// 显示调用栈
}

func TestFunc() {
	logrus.Error("test")
}
