import switchSound from "@/assets/switch.mp3";

const createSound = (url) => {
  const audio = new Audio(url);
  audio.play();
  return () => {
    audio.currentTime = 0;
    audio.play();
  };
};

const sounds = {
  switch: createSound(switchSound),
};

export default {
  playSwitchSound: () => sounds.switch(),
  playClickSound: () => sounds.switch(),
};
