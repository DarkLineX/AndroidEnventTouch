#include <jni.h>
#include <string>
#include <asm-generic/fcntl.h>
#include <fcntl.h>
#include <unistd.h>
#include <jni.h>
#include <linux/input.h>

extern "C" JNIEXPORT void JNICALL
Java_com_android_untouch_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {


    int fd = open("/dev/input/eventX", O_WRONLY | O_NONBLOCK);
    if (fd < 0) {
        return;
    }

    struct input_event ev;
    memset(&ev, 0, sizeof(ev));
    ev.type = EV_ABS;
    ev.code = ABS_MT_POSITION_X;
    ev.value = 500;
    write(fd, &ev, sizeof(ev));

    ev.code = ABS_MT_POSITION_Y;
    ev.value = 800;
    write(fd, &ev, sizeof(ev));

    ev.type = EV_SYN;
    ev.code = SYN_REPORT;
    ev.value = 0;
    write(fd, &ev, sizeof(ev));

    ev.type = EV_ABS;
    ev.code = ABS_MT_TRACKING_ID;
    ev.value = -1;
    write(fd, &ev, sizeof(ev));

    ev.type = EV_SYN;
    ev.code = SYN_REPORT;
    ev.value = 0;
    write(fd, &ev, sizeof(ev));

    close(fd);



}