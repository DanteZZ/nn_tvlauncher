let androidFunctions = {};
if (window._android_) {
  androidFunctions = {
    installedApps: () => JSON.parse(window._android_.installedApps()),
    screenSize: () => JSON.parse(window._android_.screenSize()),
    updateLoader: (state) => window._android_.updateLoader(state),
    showToast: (text) => window._android_.showToast(text),
    openSettings: () => window._android_.openSettings(),
    runApplication: (name) => window._android_.runApplication(name),
    packageName: () => window._android_.packageName(),
  };
} else {
  androidFunctions = {
    installedApps: () => [
      {
        hasBanner: false,
        banner: null,
        icon: "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",
        appname: "Приложение",
        pname: "com.some.app",
      },
    ],
    screenSize: () => {
      return { x: window.innerWidth, y: window.innerHeight };
    },
    updateLoader: (state) => {
      console.log(`Loader is ${state ? "show" : "hide"}`);
    },
    showToast: (text) => alert(text),
    openSettings: () => console.log("Opened settings"),
    runApplication: (name) => console.log(`Run application: ${name}`),
    packageName: () => null,
  };
}

export default androidFunctions;
