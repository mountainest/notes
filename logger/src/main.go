package main

import "fmt"
import log "github.com/sirupsen/logrus"

func InitLog() {
	log.SetFormatter(&log.TextFormatter{})
}

func main() {
	InitLog()
	fmt.Println("hello world!")
}