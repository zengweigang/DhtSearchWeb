package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Util {
	public static String getFormatSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.0");
		if (size < 1024) {
			return size + "byte";
		} else if (size < 1024l * 1024l) {
			float kbsize = size / 1024f;
			return formater.format(kbsize) + "KB";
		} else if (size < 1024l * 1024l * 1024l) {
			float mbsize = size / 1024f / 1024f;
			return formater.format(mbsize) + "MB";
		} else if (size < (1024l * 1024l * 1024l * 1024l)) {
			float gbsize = size / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + "GB";
		} else if (size < 1024 * 1024 * 1024 * 1024 * 1024) {
			float gbsize = size / 1024f / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + "TB";
		} else {
			return "未知";
		}
	}

	public static String getFormatCreatTime(long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
		String ctime = formatter.format(time);
		return ctime;
	}
}
