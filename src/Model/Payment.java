package src.Model;

import java.util.HashMap;

public class Payment {
    private static final HashMap<String, Double> roomTypeCostMap = new HashMap<String, Double>();

    static {
        roomTypeCostMap.put("Single", 50.0);
        roomTypeCostMap.put("Double", 75.0);
        roomTypeCostMap.put("Suite", 100.0);
    }

    public static double calculatePayment(String roomType, long numOfDays) {
        if (roomTypeCostMap.containsKey(roomType)) {

            double totalCost = roomTypeCostMap.get(roomType) * numOfDays;
            return totalCost;
        }
        return 0.0;
    }

    public static double getRoomCost(String roomType) {
        return roomTypeCostMap.get(roomType);
    }

}
