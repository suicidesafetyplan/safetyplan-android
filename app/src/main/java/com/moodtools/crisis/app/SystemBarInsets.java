package com.moodtools.crisis.app;

import android.app.Activity;
import android.graphics.Insets;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

/** Restores the system-bar-safe layout expected by the app's legacy screens. */
final class SystemBarInsets {
    private static final int EDGE_TO_EDGE_ENFORCED_API = 35;

    private SystemBarInsets() {
    }

    static void apply(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < EDGE_TO_EDGE_ENFORCED_API) {
            return;
        }

        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        View content = activity.findViewById(android.R.id.content);
        if (content == null || !(decorView instanceof ViewGroup)) {
            return;
        }

        window.setDecorFitsSystemWindows(false);
        WindowInsetsController controller = window.getInsetsController();
        View statusBarScrim = createStatusBarScrim(activity, (ViewGroup) decorView);

        final int initialLeft = content.getPaddingLeft();
        final int initialTop = content.getPaddingTop();
        final int initialRight = content.getPaddingRight();
        final int initialBottom = content.getPaddingBottom();

        decorView.setOnApplyWindowInsetsListener((view, windowInsets) -> {
            Insets systemBars = windowInsets.getInsets(
                    WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());

            FrameLayout.LayoutParams scrimLayoutParams =
                    (FrameLayout.LayoutParams) statusBarScrim.getLayoutParams();
            scrimLayoutParams.height = systemBars.top;
            statusBarScrim.setLayoutParams(scrimLayoutParams);

            if (controller != null) {
                controller.setSystemBarsAppearance(
                        0,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS);
                controller.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS);
            }

            content.post(() -> {
                int contentTop = systemBars.top;
                View actionBar = activity.findViewById(androidx.appcompat.R.id.action_bar_container);
                if (actionBar != null && actionBar.getVisibility() == View.VISIBLE) {
                    int[] location = new int[2];
                    actionBar.getLocationInWindow(location);
                    contentTop = Math.max(contentTop, location[1] + actionBar.getHeight());
                }

                content.setPadding(
                        initialLeft + systemBars.left,
                        initialTop + contentTop,
                        initialRight + systemBars.right,
                        initialBottom + systemBars.bottom);
            });

            return windowInsets;
        });

        decorView.requestApplyInsets();
    }

    private static View createStatusBarScrim(Activity activity, ViewGroup decorView) {
        View scrim = new View(activity);
        scrim.setBackgroundColor(resolveStatusBarColor(activity));
        scrim.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        scrim.setElevation(Float.MAX_VALUE);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0,
                Gravity.TOP);
        decorView.addView(scrim, layoutParams);
        return scrim;
    }

    private static int resolveStatusBarColor(Activity activity) {
        TypedValue value = new TypedValue();
        if (activity.getTheme().resolveAttribute(
                androidx.appcompat.R.attr.colorPrimaryDark, value, true)) {
            if (value.resourceId != 0) {
                return activity.getColor(value.resourceId);
            }
            return value.data;
        }
        return activity.getColor(R.color.mainbluealt);
    }
}
