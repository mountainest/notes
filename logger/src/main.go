package main

import "fmt"
import "github.com/sirupsen/logrus"

func InitLog() {
	logrus.SetFormatter(&logrus.TextFormatter{})
}

func main() {
	InitLog()
	fmt.Println("hello world!")
}