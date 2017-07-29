package server;

@SuppressWarnings("unused")
class ContentTypeParser {

	private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

	static String getMimeType(String fileName) {
		String extension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase() : "";
		if (extension.length() == 0)
			return DEFAULT_MIME_TYPE;
		try {
			return MimeTypes.valueOf(extension).toString();
		} catch (IllegalArgumentException | NullPointerException e) {
			return DEFAULT_MIME_TYPE;
		}
	}

	enum MimeTypes {
		jpg("image/jpeg"),
		jpeg("image/jpeg"),
		png("image/png"),
		tiff("image/tiff"),
		webp("image/webp"),
		ico("image/x-icon"),
		js("application/javascript"),
		json("application/json"),
		pdf("application/pdf"),
		css("text/css"),
		csv("text/csv"),
		html("text/html"),
		txt("text/plain"),
		midi("audio/midi"),
		aac("audio/aac"),
		mp3("audio/mp3"),
		mpeg("video/mpeg"),
		avi("video/x-msvideo");

		private String name;

		MimeTypes(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}
}