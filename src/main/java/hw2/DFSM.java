package hw2;

public class DFSM {

    public boolean check(String input){
        int state = 0;
        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            switch(state){
                case 0:
                    switch (letter){
                        case 'a':
                            state = 1;
                            break;
                        case 'b':
                            state = 0;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 1:
                    switch (letter){
                        case 'a':
                            state = 1;
                            break;
                        case 'b':
                            state = 2;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 2:
                    switch (letter){
                        case 'a':
                            state = 1;
                            break;
                        case 'b':
                            state = 0;
                            break;
                        case 'c':
                            state = 3;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 3:
                    switch (letter){
                        case 'a':
                            state = 4;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 4:
                    switch (letter){
                        case 'a':
                            state = 5;
                            break;
                        case 'b':
                            state = 6;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 5:
                    switch (letter){
                        case 'b':
                            state = 7;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 6:
                    switch (letter){
                        case 'c':
                            state = 3;
                            break;
                        default:
                            return false;
                    }
                    break;
                case 7:
                    switch (letter){
                        case 'b':
                            state = 8;
                            break;
                        default:
                            return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
}
