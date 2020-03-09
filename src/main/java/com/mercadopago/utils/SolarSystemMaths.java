package com.mercadopago.utils;

import javafx.geometry.Point2D;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.StrictMath.sin;

public class SolarSystemMaths {

    /**
     * Obtengo el punto X/Y sobre la circunferencia de un circulo a partir de su angulo y radius
     *
     * @param angle  - angulo
     * @param radius - radio
     * @return - retorna el punto X/Y
     */
    public static Point2D getCircumferencePoint(int angle, long radius) {

        // Tomo hasta 8 decimales para no introducir errores
        DecimalFormat df = new DecimalFormat("#.########");

        double x = cos(toRadians(angle)) * radius;
        x = Double.parseDouble(df.format(x).replace(",", "."));

        double y = sin(toRadians(angle)) * radius;
        y = Double.parseDouble(df.format(y).replace(",", "."));

        return new Point2D(x, y);

    }

    public static boolean isLine(List<Point2D> pointList) {

        if (pointList.size() < 2)
            return false;

        Point2D point0 = pointList.get(0);
        Point2D point1 = pointList.get(1);

        double gSlope = slope(point0, point1);

        for (int i = 1; i < pointList.size(); i++) {

            Point2D pointI = pointList.get(i);
            double slope = slope(point0, pointI);

            // Porcentaje de error en calculos, al convertir de angulo a punto cartesiano
            if (abs(gSlope - slope) > 0.1)
                return false;

        }

        return true;

    }

    private static double slope(Point2D point1, Point2D point2) {

        if ((point1.getX() - point2.getX()) == 0)
            return 0;

        return (point2.getY() - point1.getY()) / (point2.getX() - point1.getX());

    }

    public static boolean isInside(List<Point2D> polygon, Point2D point) {

        return contains(polygon, point);

    }

    private static boolean contains(List<Point2D> points, Point2D test) {

        int i;
        int j;
        boolean result = false;

        for (i = 0, j = points.size() - 1; i < points.size(); j = i++) {

            if ((points.get(i).getY() > test.getY()) != (points.get(j).getY() > test.getY()) &&
                    (test.getX() < (points.get(j).getX() - points.get(i).getX()) * (test.getY() - points.get(i).getY()) / (points.get(j).getY() - points.get(i).getY()) + points.get(i).getX())) {
                result = !result;
            }

        }

        return result;

    }

}
