package main

import (
	"fmt"
	"logger/src/logger"

	"github.com/sirupsen/logrus"
)

func main() {
	logger.InitLogger()
	logrus.Error("test")
	fmt.Println("hello world!")
}
