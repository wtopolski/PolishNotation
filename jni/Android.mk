LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS := -llog

LOCAL_MODULE    := JniHelper
LOCAL_SRC_FILES := JniHelper.cpp

include $(BUILD_SHARED_LIBRARY)