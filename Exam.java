
import java.util.*;

public class Exam {

    public static double calculateConstantTerm(JSONObject jsonInput) {
        JSONObject keys = jsonInput.getJSONObject("keys");
        int n = keys.getInt("n");
        int k = keys.getInt("k");

        Map<Integer, Point> points = new HashMap<>();

        for (String key : jsonInput.keySet()) {
            if (!key.equals("keys")) {
                JSONObject pointJson = jsonInput.getJSONObject(key);
                int x = Integer.parseInt(pointJson.getString("value"), Integer.parseInt(pointJson.getString("base")));
                int y = x; 

                points.put(x, new Point(x, y));
            }
        }

        double constantTerm = 0;

        for (Point point : points.values()) {
            double term = point.y;

            for (Point otherPoint : points.values()) {
                if (point.x != otherPoint.x) {
                    term *= (0 - otherPoint.x) / (point.x - otherPoint.x);
                }
            }

            constantTerm += term;
        }

        return constantTerm;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        String jsonInput = "{\"keys\": {\"n\": 4, \"k\": 3}, \"1\": {\"base\": \"10\", \"value\": \"4\"}, \"2\": {\"base\": \"2\", \"value\": \"111\"}, \"3\": {\"base\": \"10\", \"value\": \"12\"}, \"6\": {\"base\": \"4\", \"value\": \"213\"}}";

        JSONObject jsonObject = new JSONObject(jsonInput);
        double constantTerm = calculateConstantTerm(jsonObject);

        System.out.println("Constant Term (c): " + constantTerm);
    }
}



