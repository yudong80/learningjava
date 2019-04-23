package demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Problem02 {

    @Test
    public void test() {
        String s = "00:01:07,400-234-090\n" +
                   "00:05:01,701-080-080\n" +
                   "00:05:00,400-234-090";

        assertEquals(new Problem02().solution(s), 900);
    }

    public int solution(String S) {
        String[] lines = S.split("\n");
        Map<String, PhoneBill> map = new HashMap<>();
        for (String line : lines) {
            PhoneBill bill = new PhoneBill(line);
            String key = bill.getPhoneNumber();

            if (map.containsKey(key)) {
                PhoneBill oldBill = map.get(key);
                oldBill.updateData(line);
            } else {
                map.put(key, bill);
            }
        }

        //증복 제거
        List<PhoneBill> list = new ArrayList<>();
        for (String key : map.keySet()) {
            PhoneBill bill = map.get(key);
            list.add(bill);
            //System.out.println("phone : " + key + " >> duration : " + bill.getDuration() + " >> price : " + bill.getPrice());
        }

        int maxIdx = -1;
        int maxDuration = -1;
        int minNumericalValue = -1;
        for (int i=0; i< list.size(); ++i) {
            PhoneBill bill = list.get(i);
            int duration = bill.getDuration();
            if ((duration > maxDuration) ||
                (duration == maxDuration && bill.getPhoneNumerical() < minNumericalValue)) { //for tie
                maxIdx = i;
                maxDuration = bill.getDuration();
                minNumericalValue = bill.getPhoneNumerical();
            }
        }

        System.out.println("max index : " + maxIdx);

        //total price
        int totalPrice = 0;
        for (int i=0; i < list.size(); ++i) {
            if (i == maxIdx) continue;

            int price = list.get(i).getPrice();
            System.out.println("price : " + price);
            totalPrice += price;
        }

        System.out.println("total price : " + totalPrice);
        return totalPrice;
    }

    class PhoneBill {
        private String phoneNumber;
        private int duration; //seconds
        private int phoneNumerical = 0;
        private int price; //cents

        public PhoneBill(String str) {
            String[] parsed = str.split(",");
            String phone = parsed[1];
            this.phoneNumber = phone;
            String[] phoneParsed = phone.split("-");
            this.phoneNumerical = parsePhoneNumerical(phoneParsed);

            parseData(str);
        }

        public void updateData(String str) {
            String[] parsed = str.split(",");
            String time = parsed[0];

            String[] timeParsed = time.split(":");
            this.duration += parseDuration(timeParsed);
            this.price += checkoutPrice(duration);
        }

        private int parsePhoneNumerical(String[] phoneParsed) {
            return Integer.parseInt(phoneParsed[0]) * (1000 * 1000) +
                    Integer.parseInt(phoneParsed[1]) * (1000) +
                    Integer.parseInt(phoneParsed[2]);
        }

        private void parseData(String str) {
            String[] parsed = str.split(",");
            String time = parsed[0];

            String[] timeParsed = time.split(":");
            this.duration = parseDuration(timeParsed);
            this.price = checkoutPrice(duration);
        }

        private int parseDuration(String[] timeParsed) {
            return Integer.parseInt(timeParsed[0]) * (60 * 60) +
                    Integer.parseInt(timeParsed[1]) * (60) +
                    Integer.parseInt(timeParsed[2]);
        }

        private int checkoutPrice(int seconds) {
            if (seconds < 5 * 60) return seconds * 3;

            int minutes = (int) Math.ceil(((double)seconds) / 60);
            //System.out.println("minutes : " + minutes);
            return minutes * 150;
        }

        public String getPhoneNumber() { return this.phoneNumber; }
        public int getDuration() { return this.duration; }
        public int getPhoneNumerical() { return this.phoneNumerical; }
        public int getPrice() { return this.price; }
    }
}
