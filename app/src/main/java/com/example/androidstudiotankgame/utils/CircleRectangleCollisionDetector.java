package com.example.androidstudiotankgame.utils;

import androidx.annotation.NonNull;

import com.example.androidstudiotankgame.gameobject.Circle;
import com.example.androidstudiotankgame.gameobject.Rectangle;

public class CircleRectangleCollisionDetector {
    private static double[][] rotated_coordinates;

    public static boolean isCircleTouchingRectangle(Circle circle, @NonNull Rectangle rectangle) {
        int rect_x1 = (int) rectangle.getX1();
        int rect_y1 = (int) rectangle.getY1();
        int rect_x2 = (int) rectangle.getX2();
        int rect_y2 = (int) rectangle.getY2();
        double angle = rectangle.getRotationAngle();

        // Calculate rectangle corners relative to its center
        int rect_cx = (rect_x1 + rect_x2) / 2;
        int rect_cy = (rect_y1 + rect_y2) / 2;
        int[] x_coordinates = {rect_x1 - rect_cx, rect_x2 - rect_cx, rect_x2 - rect_cx, rect_x1 - rect_cx};
        int[] y_coordinates = {rect_y1 - rect_cy, rect_y1 - rect_cy, rect_y2 - rect_cy, rect_y2 - rect_cy};

        // Calculate rotation matrix
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        // Precompute rotated coordinates of rectangle corners
        rotated_coordinates = new double[4][2];
        for (int i = 0; i < 4; i++) {
            double dx = x_coordinates[i];
            double dy = y_coordinates[i];
            rotated_coordinates[i][0] = dx * cos - dy * sin + rect_cx;
            rotated_coordinates[i][1] = dx * sin + dy * cos + rect_cy;
        }

        int cx = (int) circle.getPositionX();
        int cy = (int) circle.getPositionY();
        int radius = (int) circle.getRadius();

        // Check if circle is touching any side of the rectangle
        for (int i = 0; i < 4; i++) {
            double x1 = rotated_coordinates[i][0];
            double y1 = rotated_coordinates[i][1];
            double x2 = rotated_coordinates[(i + 1) % 4][0];
            double y2 = rotated_coordinates[(i + 1) % 4][1];
            double dist = distToSegment(cx, cy, x1, y1, x2, y2);
            if (dist <= radius) {
                return true;
            }
        }

        return false;
    }

    // Helper method to compute distance from a point to a line segment
    private static double distToSegment(double x, double y, double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dot = (x - x1) * dx + (y - y1) * dy;
        double len = dx * dx + dy * dy;
        double t = Math.min(1, Math.max(0, dot / len));
        double px = x1 + t * dx;
        double py = y1 + t * dy;
        return Math.sqrt((x - px) * (x - px) + (y - py) * (y - py));
    }
}
