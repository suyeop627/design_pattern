package org.designpattern.structural_pattern.Proxy.Virtual;

public class VirtualProxyVedio implements Video {
  private final String videoPath;
  private final String thumbnailPath;
  private RealVideo realVideo;

  public VirtualProxyVedio(String videoPath, String thumbnailPath){
    this.videoPath = videoPath;
    this.thumbnailPath = thumbnailPath;
  }


  @Override
  public void showThumbnailImage() {
    System.out.println("Showing Thumbnail Image : "+thumbnailPath);
  }

  @Override
  public void playVideo() {
    if(realVideo==null){
      realVideo = new RealVideo(videoPath, thumbnailPath);
      System.out.println("RealVideo is created");
    }
    realVideo.playVideo();
  }
}
