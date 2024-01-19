package org.designpattern.structural_pattern.Proxy.Virtual;

// 가상 프록시와 실제 객체가 구현하는 인터페이스
public interface Video {
  //썸네일은 프록시에서 처리 가능하도록 함
  void showThumbnailImage();
  //영상 재생(무거운작업)이 필요할 때, 실제 비디오 객체 생성함.
  void playVideo();
}
