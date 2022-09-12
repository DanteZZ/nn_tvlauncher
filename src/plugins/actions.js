import android from "@/config/android";

const actions = {
  settings: () => {
    android.openSettings();
  },
  store: () => {
    android.runApplication("org.eltex.AppStore");
  },
  tv: () => {
    android.runApplication("org.eltex.android.app.iptvplayer");
  },
};

export const runAction = (id) => {
  try {
    if (actions[id]) {
      actions[id]();
    } else {
      throw new Error();
    }
  } catch (e) {
    android.showToast(`Не удаётся выполнить: ${id}`);
  }
};
