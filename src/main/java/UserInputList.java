

import java.util.ArrayList;
import java.util.List;

public class UserInputList {
    private List<InputItem> inputs;
    public UserInputList() {
        this.inputs = new ArrayList<>();
    }

    public void add(String input) {
        inputs.add(new InputItem(input,false));
    }

    public void remove(int index) {
        if( index > 0 && index <= inputs.size()){
            inputs.remove(index);
        } else{
            System.out.println("Index out of bounds");
        }
    }

    public void mark(int index){
        if(index > 0 && index < inputs.size()){
            InputItem item = inputs.get(index);
            if(item.isMarked()){
                System.out.println("Already Marked");
                return;
            }
            item.setMarked(true);
        } else{
            System.out.println("Index out of bounds");
        }
    }

    public void unmark(int index){
        if(index > 0 && index < inputs.size()){
            InputItem item = inputs.get(index);
            if(item.isMarked()){
                item.setMarked(false);
            }
        } else{
            System.out.println("Invalid Index");
        }
    }

    public List<InputItem> getInputs() {
        return inputs;
    }

    public void clear() {
        inputs.clear();
    }

    public void printInputs() {
        if (inputs.isEmpty()) {
            System.out.println("No Inputs");
        } else {
            int index = 1;
            for (InputItem input : inputs) {
                String markStatus = input.isMarked() ? "[X]" : "[ ]";
                System.out.println(index + ". " + markStatus + " " + input.getInput());
                index++;
            }
        }
    }

}

class InputItem {
    private String input;
    private boolean isMarked;

    public InputItem(String input, boolean isMarked) {
        this.input = input;
        this.isMarked = isMarked;
    }

    public String getInput() {
        return input;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

}
