import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Saves {
    private static Input input = new Input();

    public static void saveAndExit(Player player) {
        SaveData data = new SaveData(player);
        Misc.screenSpace();
        File.addLine(Game.getSaveTo(), data.toFileString());
        System.out.println("Your save file has been saved! Good bye!");
        System.exit(0);
    }

    public static Player loadGameScreen(List<String> saves) {       //loading and deleting saves
        while (true) {
            Misc.screenSpace();
            List<SaveData> saveDataList = new ArrayList<>();

            for (String line : saves) {
                saveDataList.add(new SaveData(line));
            }
            Collections.sort(saveDataList);         // automatikusan a compareTo() metódust használja

            System.out.println("========== SAVED GAMES ==========");

            for (int i = 0; i < saveDataList.size(); i++) {
                saveDataList.get(i).printSummary(i + 1);
                System.out.println("----------------------------");
            }

            if (saveDataList.isEmpty()) {
                System.out.println("There are no saves!");
                input.getInput("Press enter to go back...");
                return null;
            }

            ArrayList<String> options = new ArrayList<>();

            for (int i = 1; i <= saveDataList.size(); i++) {
                options.add(String.valueOf(i));
            }

            options.add("delete");
            options.add("back");

            String choice = input.getInput("Choose a save to load, or type 'delete' to delete a save:", options);

            if (choice.equals("back")) {
                return null;
            }

            if (choice.equals("delete")) {
                ArrayList<String> deleteOptions = new ArrayList<>();
                for (String option : options) {
                    if (!option.equals("delete") && !option.equals("back")) {
                        deleteOptions.add(option);
                    }
                }

                String deleteChoice = input.getInput("Which save do you want to delete?", deleteOptions);

                int index = Integer.parseInt(deleteChoice) - 1;
                int selectedId = saveDataList.get(index).getId();

                // Megkeressük az eredeti sorszámát a fájlban, ami most a saves List-ben van
                int fileIndex = -1;
                for (int i = 0; i < saves.size(); i++) {
                    SaveData fileSave = new SaveData(saves.get(i));

                    if (fileSave.getId() == selectedId) {
                        fileIndex = i;
                        break;
                    }
                }

                if (fileIndex != -1) {
                    File.deleteLine(Game.getSaveTo(), fileIndex);
                    saves.remove(fileIndex);
                    System.out.println("Save deleted!");
                }

                input.getInput("Press enter to continue...");

            } else {
                int index = Integer.parseInt(choice) - 1;
                return saveDataList.get(index).createPlayer();
            }
        }
    }
}
