import android from "@/config/android";

const { x } = android.screenSize();

const minWidth = 1280;
const maxWidth = 1680;

const needWidth = x < minWidth ? minWidth : x > maxWidth ? maxWidth : x;

const updateDpi = () => {
  const scale = window.screen.width / needWidth;
  document
    .querySelector("meta[name=viewport]")
    .setAttribute(
      "content",
      `width=device-width, initial-scale=${scale}, maximum-scale=${scale}, user-scalable=0`
    );
};

export default updateDpi;
