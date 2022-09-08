import android from "@/config/android";

const actions = {
  settings: () => {
    android.openSettings();
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
