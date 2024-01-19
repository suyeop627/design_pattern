package org.designpattern.structural_pattern.Proxy.Virtual;

public class Client {
  public static void main(String[] args) {
      // 가상 프록시를 통해 Video 객체를 사용함
      Video video = new VirtualProxyVedio("example.mp4","thumbnail.jpg");

      video.showThumbnailImage();

      video.showThumbnailImage();

    // 영상을 재생하는 시점에 실제 객체가 생성되고, 무거운 작업이 수행됨
      video.playVideo();
    }
}
