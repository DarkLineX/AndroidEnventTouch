#### 使用getenvent捕获事件

```shell
130|mars:/dev/input $ getevent

add device 1: /dev/input/event6
  name:     "lahaina-mtp-snd-card Button Jack"
  
add device 2: /dev/input/event5
  name:     "lahaina-mtp-snd-card Headset Jack"
  
add device 3: /dev/input/event4
  name:     "fts"
  
add device 4: /dev/input/event3
  name:     "uinput-goodix"
  
add device 5: /dev/input/event0
  name:     "qpnp_pon"
  
add device 6: /dev/input/event2
  name:     "gpio-keys"
  
add device 7: /dev/input/event1
  name:     "aw8697_haptic"
  
/dev/input/event4: 0003 0039 00000689
/dev/input/event4: 0001 014a 00000001
/dev/input/event4: 0001 0145 00000001
/dev/input/event4: 0003 0035 00000273
/dev/input/event4: 0003 0036 0000069d
/dev/input/event4: 0003 0030 00000060
/dev/input/event4: 0003 0031 00000050
/dev/input/event4: 0003 0034 00000042
/dev/input/event4: 0000 0000 00000000
/dev/input/event4: 0003 0039 ffffffff
/dev/input/event4: 0001 014a 00000000
/dev/input/event4: 0001 0145 00000000
/dev/input/event4: 0000 0000 00000000
```

其中不同的event代表不同的输入设备

比如我们点击屏幕触发的event4，表示屏幕输入事件就是event4

- event0  "qpnp_pon"
- event1  "aw8697_haptic"
- event2  "gpio-keys"
- event3  "uinput-goodix"
- event4  "fts"                                 FocalTech Systems 生产的触摸屏控制器
- event5  "lahaina-mtp-snd-card Headset Jack"
- event6  "lahaina-mtp-snd-card Button Jack"