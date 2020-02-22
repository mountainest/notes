package main

import (
	"fmt"
	"logger/src/logger"
)

func main() {
	logger.InitLogger("./../.log/", "logger", "debug")
	logger.TestFunc()
	fmt.Println("hello world!")
}
