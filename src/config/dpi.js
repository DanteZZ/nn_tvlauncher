const minWidth = 1280;

const updateDpi = () => {
  if (window.screen.width < minWidth && window.Detector.devicePixelRatio > 1) {
    const scale = window.screen.width / minWidth;
    document
      .querySelector("meta[name=viewport]")
      .setAttribute(
        "content",
        `width=device-width, initial-scale=${scale}, maximum-scale=${scale}, user-scalable=0`
      );
  }
};

export default updateDpi;
