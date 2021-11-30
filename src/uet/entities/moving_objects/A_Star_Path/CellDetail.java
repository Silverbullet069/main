//package uet.entities.moving_objects.A_Star_Path;
//
//import javafx.util.Pair;
//
//public class CellDetail implements Comparable<Object> {
//    private Pair<Double, Pair<Integer, Integer>> detail;
//
//    public CellDetail(Double dou, Pair<Integer, Integer> intPair) {
//        detail = new Pair<>(dou, intPair);
//    }
//
//    public CellDetail(Double dou, Integer int1, Integer int2) {
//        detail = new Pair<>(dou, new Pair<Integer, Integer>(int1, int2));
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        if (o.getClass().isInstance(Pair.class)) {
//            Pair<Double, Pair<Integer, Integer>> pair2 = (Pair<Double, Pair<Integer, Integer>>) o;
//            if (this.getKey().equals(pair2.getKey())) {
//                if (this.getValue().getKey().equals(pair2.getValue().getKey())) {
//                    return this.getValue().getValue().compareTo(pair2.getValue().getValue());
//                } else {
//                    return this.getValue().getKey().compareTo(pair2.getValue().getKey());
//                }
//            } else {
//                return this.getKey().compareTo(pair2.getKey());
//            }
//        }
//        return 1;
//    }
//
//    public Double getKey() {
//        return this.detail.getKey();
//    }
//
//    public Pair<Integer, Integer> getValue() {
//        return this.detail.getValue();
//    }
//
//    public Pair<Double, Pair<Integer, Integer>> getDetail() {
//        return detail;
//    }
//}