package main

import (
	"fmt"
	"logger/src/logger"
)

func main() {
	logger.InitLogger()
	logger.TestFunc()
	fmt.Println("hello world!")
}
