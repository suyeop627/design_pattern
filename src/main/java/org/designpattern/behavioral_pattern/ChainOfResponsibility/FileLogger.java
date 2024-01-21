package org.designpattern.behavioral_pattern.ChainOfResponsibility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//Concrete Handler
class FileLogger extends Logger {
  private String filePath = "C:/Users/window/Desktop/logger/error_log.txt";

  @Override
  protected boolean shouldHandle(LogLevel level) {
    return level == LogLevel.ERROR;
  }

  @Override
  protected void handleLogMessage(LogMessage logMessage) {
    // ERROR 레벨의 로그를 파일에 저장
    if(isAvailableDirectory(filePath)){
      //PrintWriter를 사용하여 파일에 쓰기를 수행
      //FileWriter는 파일에 문자를 쓰기 위한 클래스이며, true를 전달하여 파일을 이어쓰기 모드 지정
      //PrintWriter는 보다 편리한 형식으로 출력할 수 있도록 도와주는 클래스
      try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
        // 로그를 파일에 추가
        writer.println("ERROR: " + logMessage.getMessage());
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("Error log massage is written on "+filePath);
    }else{
      System.out.println("Failed to logging on file(path: "+filePath+")");
    }

  }

  // 디렉토리가 존재하는지 확인하고, 없으면 생성
  private boolean isAvailableDirectory(String filePath) {
    File file = new File(filePath);
    File parentDir = file.getParentFile();
    //파일의 상위 디렉토리가 없는데 생성에 실패하면 종료
    if (!parentDir.exists() && !parentDir.mkdirs()) {
      System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
      return false;
    }
    return true;
  }
}
