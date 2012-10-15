LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS := -llog

LOCAL_MODULE    := polish-notation
LOCAL_SRC_FILES := token.cpp \
operand.cpp \
operator.cpp \
support.cpp  \
JniHelper.cpp \

include $(BUILD_SHARED_LIBRARY)
