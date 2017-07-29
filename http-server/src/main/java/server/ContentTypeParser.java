package server;

import java.util.HashSet;

@SuppressWarnings("unused")
class ContentTypeParser {

	private static HashSet<Class> enumSet = new HashSet<>();

	static {
		enumSet.add(ImageTypes.class);
		enumSet.add(ApplicationTypes.class);
		enumSet.add(TextTypes.class);
		enumSet.add(AudioTypes.class);
		enumSet.add(VideoTypes.class);
	}

	static String getFileType(String name) {
		String extension = name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : "";
		if (extension.length() == 0)
			return "text/html";
		Enum element;
		for (Class c : enumSet)
			if ((element = valueOf(c, extension)) != null)
				return element.toString();
		return "text/html";
	}

	private static <T extends Enum<T>> T valueOf(Class<T> tClass, String elementName) {
		for (T element : tClass.getEnumConstants())
			if (element.name().equals(elementName))
				return element;
		return null;
	}

	enum ImageTypes {
		jpg("jpeg"), jpeg, png, tiff, webp, ico("x-icon");

		private String name;

		ImageTypes() {
		}

		ImageTypes(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "image/" + (name == null ? name() : name);
		}
	}

	enum ApplicationTypes {
		js("javascript"), json, pdf;

		private String name;

		ApplicationTypes() {
		}

		ApplicationTypes(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "application/" + (name == null ? name() : name);
		}
	}

	enum TextTypes {
		css, csv, html;

		@Override
		public String toString() {
			return "text/" + name();
		}
	}

	enum AudioTypes {
		midi, aac, mp3;

		@Override
		public String toString() {
			return "audio/" + name();
		}
	}

	enum VideoTypes {
		mpeg, avi("x-msvideo");

		private String name;

		VideoTypes() {
		}

		VideoTypes(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "video/" + (name == null ? name() : name);
		}
	}
}