package egovframework.com.cmm.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import com.xuggle.mediatool.IMediaReader;
//import com.xuggle.mediatool.MediaListenerAdapter;
//import com.xuggle.mediatool.ToolFactory;
//import com.xuggle.mediatool.event.IVideoPictureEvent;

public class VideoThumbnailExtractor {

//	public static final double SECONDS_BETWEEN_FRAMES = 1;
//	public static final long NO_PTS =  -9223372036854775808l;
//	public static final long DEFAULT_PTS_PER_SECOND =  1000000l;
//
//	// The video stream index, used to ensure we display frames from one and
//	// only one video stream from the media container.
//	private static int mVideoStreamIndex = -1;
//
//	// Time of last frame write
//	private static long mLastPtsWrite = NO_PTS;
//
//	public static final long MICRO_SECONDS_BETWEEN_FRAMES = (long) (DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);
//
//	public static void storeThumbnail(String videoFileName, String storeFileName, String storeFileExt) {
//
//		IMediaReader mediaReader = ToolFactory.makeReader(videoFileName);
//
//		// stipulate that we want BufferedImages created in BGR 24bit color
//		// space
//
//		try {
//			mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
//
//			ImageSnapListener isListener = new ImageSnapListener(storeFileName, storeFileExt);
//			mediaReader.addListener(isListener);
//
//			// read out the contents of the media file and
//			// dispatch events to the attached listener
//
//			while (!isListener.isImageGrabbed()) {
//				mediaReader.readPacket();
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//
//	}
//
//	private static class ImageSnapListener extends MediaListenerAdapter {
//		public boolean imageGrabbed = false;
//
//		private String storeFileName = null;
//		private String storeFileExt = null;
//
//		public ImageSnapListener(String storeFileName, String storeFileExt) {
//			this.storeFileName = storeFileName;
//			this.storeFileExt = storeFileExt;
//		}
//
//		public void onVideoPicture(IVideoPictureEvent event) {
//
//			if (event.getStreamIndex() != mVideoStreamIndex) {
//
//				// if the selected video stream id is not yet set, go ahead an
//				// select this lucky video stream
//
//				if (mVideoStreamIndex == -1) mVideoStreamIndex = event.getStreamIndex();
//
//				// no need to show frames from this video stream
//				else return;
//
//			}
//
//			// if uninitialized, back date mLastPtsWrite to get the very first
//			// frame
//
//			if (mLastPtsWrite == NO_PTS)
//
//				mLastPtsWrite = event.getTimeStamp()
//					- MICRO_SECONDS_BETWEEN_FRAMES;
//
//			// if it's time to write the next frame
//
////			if (event.getTimeStamp() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES)
//			if (event.getTimeStamp() - mLastPtsWrite >= (MICRO_SECONDS_BETWEEN_FRAMES * 3)) {
//
//				String outputFilename = dumpImageToFile(event.getImage());
//
//				this.imageGrabbed = true; //set this var to true once an image is grabbed out of the movie.
//
//				// indicate file written
//				double seconds = ((double) event.getTimeStamp()) / DEFAULT_PTS_PER_SECOND;
//
//				System.out.printf("at elapsed time of %6.3f seconds wrote: %s\n", seconds, outputFilename);
//				//System.out.printf("at elapsed time of %6.3f seconds wrote: SOMEFILE\n",seconds);
//
//				// update last write time
//				mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
//
//			}
//
//		}
//
//		private String dumpImageToFile(BufferedImage image) {
//
//			try {
//
//				String outputFilename = storeFileName + "." + storeFileExt;
//				ImageIO.write(image, storeFileExt, new File(outputFilename));
//				return outputFilename;
//
//			} catch (IOException e) {
//				e.printStackTrace();
//				return null;
//			}
//
//		}
//
//		public boolean isImageGrabbed() {
//			return imageGrabbed;
//		}
//
//	}
}
