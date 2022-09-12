export const SCR_APPS = "apps";
export const SCR_TV = "tv";
export const SCR_STORE = "store";
export const SCR_SETTINGS = "settings";

export const defaultScreen = SCR_APPS;

export default [
  { id: "apps", name: "Приложения" },
  { id: "tv", name: "Каналы", action: true },
  { id: "store", name: "Магазин", action: true },
  { id: "settings", name: "Настройки", action: true },
];
