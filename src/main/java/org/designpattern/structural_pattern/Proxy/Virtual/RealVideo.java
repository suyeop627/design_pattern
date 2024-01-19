package org.designpattern.structural_pattern.Proxy.Virtual;

// 실제로 무거운 작업을 수행하는 클래스
public class RealVideo implements Video {
  private final String videoPath;
  private final String thumbnailPath;

  public RealVideo(String videoPath, String thumbnailPath) {
    this.videoPath = videoPath;
    this.thumbnailPath = thumbnailPath;
    loadVideoFromDisk();
  }

  //무거운 작업을 가정
  private void loadVideoFromDisk() {
    try{
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Loading video from disk: " + videoPath);
  }

  @Override
  public void showThumbnailImage() {
    System.out.println("Showing Thumbnail Image : "+thumbnailPath);
  }

  @Override
  public void playVideo() {
    System.out.println("Playing video: " + videoPath);
  }
}

