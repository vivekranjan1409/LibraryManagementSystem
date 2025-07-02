package project.LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class FineService {
    private final List<Fine> fineList = new ArrayList<>();

    public void addFine(Fine fine){
        fineList.add(fine);
    }


    public List<Fine> getFinesByUser(User user) {
        List<Fine> result = new ArrayList<>();
        for (Fine fine : fineList) {
            if (fine.getUser().getUserId() == user.getUserId()) {
                result.add(fine);
            }
        }
        return result;
    }

    public List<Fine> getUnpaidFines(User user) {
        List<Fine> result = new ArrayList<>();
        for (Fine fine : fineList) {
            if (fine.getUser().getUserId() == user.getUserId() && !fine.isPaid()) {
                result.add(fine);
            }
        }
        return result;
    }

    public boolean payFine(int fineId, User user) {
        for (Fine fine : fineList) {
            if (fine.getFineId() == fineId && fine.getUser().getUserId() == user.getUserId()) {
                if (!fine.isPaid()) {
                    fine.payFine();
                    System.out.println("Fine paid successfully.");
                    return true;
                } else {
                    System.out.println("Fine is already paid.");
                    return false;
                }
            }
        }
        System.out.println("Fine not found or does not belong to this user.");
        return false;
    }

    public void showAllFines() {
        if (fineList.isEmpty()) {
            System.out.println("No fines in the system.");
            return;
        }
        for (Fine fine : fineList) {
            System.out.println(fine);
        }
    }



}
