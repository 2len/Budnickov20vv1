
public class GameField {

    public int[][] Id = new int[4][4];
    int max = 1;
    public int score = 0;

    GameField() {//заполняем поле квадратами
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Id[i][j] = 0;
            }
        }
        //Id[0][0] = 10;
        //Id[0][1] = 10;
    }

    public int NewElement() {
        int apopit = 0;
        int bpopit = 0;
        int find = 0;
        int[][] popit = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                popit[i][j] = 0;
            }
        }
        while (find < 15) {
            int a = 0 + (int) (Math.random() * 4);
            int b = 0 + (int) (Math.random() * 4);
            if (Id[a][b] == 0) {
                Id[a][b] = 1;
                break;
            } else {
                if (popit[a][b] == 0) {
                    popit[a][b] = 1;
                    find++;
                }
            }
        }
        return find;
    }

    public int UP() {//сдвиг блоков вверх
        int sdvig = 0;
        int chet = 0;
        // while(chet<3){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != 3) {
                    if (Id[i][j] == Id[i][j + 1]) {
                        if (Id[i][j] != 0) {//проверяем можем ли объединить
                            Id[i][j]++;
                            score += Math.pow(2, Id[i][j]);
                            if (Id[i][j] > max) {
                                max = Id[i][j];
                            }
                            Id[i][j + 1] = 0;
                            sdvig++;
                        }
                    }
                }
                if (0 != j) {
                    if (Id[i][j] != 0) {
                        if (Id[i][j - 1] == 0) {
                            sdvig++;//проверяем можно ли сдвинуть
                            Id[i][j - 1] = Id[i][j];
                            Id[i][j] = 0;
                        }
                    }
                }
            }
        }
        // }
        return sdvig;
    }

    public int DOWN() {//сдвиг блоков вверх
        int sdvig = 0;
        int chet = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > -1; j--) {
                if (j != 0) {
                    if (Id[i][j] == Id[i][j - 1]) {
                        if (Id[i][j] != 0) {//проверяем можем ли объединить
                            Id[i][j]++;
                            score += Math.pow(2, Id[i][j]);
                            if (Id[i][j] > max) {
                                max = Id[i][j];
                            }
                            Id[i][j - 1] = 0;
                            sdvig++;
                        }
                    }
                }
                if (3 != j) {
                    if (Id[i][j] != 0) {
                        if (Id[i][j + 1] == 0) {
                            sdvig++;//проверяем можно ли сдвинуть
                            Id[i][j + 1] = Id[i][j];
                            Id[i][j] = 0;
                        }
                    }
                }
            }
        }
        chet++;

        return sdvig;
    }

    public int LEFT() {//сдвиг блоков вверх
        int sdvig = 0;
        int chet = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if (i != 3) {
                    if (Id[i + 1][j] == Id[i][j]) {
                        if (Id[i][j] != 0) {//проверяем можем ли объединить
                            Id[i][j]++;
                            score += Math.pow(2, Id[i][j]);
                            if (Id[i][j] > max) {
                                max = Id[i][j];
                            }
                            Id[i + 1][j] = 0;
                            sdvig++;
                        }
                    }
                }
                if (0 != i) {
                    if (Id[i][j] != 0) {
                        if (Id[i - 1][j] == 0) {
                            sdvig++;//проверяем можно ли сдвинуть
                            Id[i - 1][j] = Id[i][j];
                            Id[i][j] = 0;
                        }
                    }
                }
            }
        }
        chet++;

        return sdvig;
    }

    public int RIGHT() {//сдвиг блоков вверх
        int sdvig = 0;
        int chet = 0;
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > -1; i--) {
                if (i != 0) {
                    if (Id[i - 1][j] == Id[i][j]) {
                        if (Id[i][j] != 0) {//проверяем можем ли объединить
                            Id[i][j]++;
                            score += Math.pow(2, Id[i][j]);
                            if (Id[i][j] > max) {
                                max = Id[i][j];
                            }
                            Id[i - 1][j] = 0;
                            sdvig++;
                        }
                    }
                }
                if (3 != i) {
                    if (Id[i][j] != 0) {
                        if (Id[i + 1][j] == 0) {
                            sdvig++;//проверяем можно ли сдвинуть
                            Id[i + 1][j] = Id[i][j];
                            Id[i][j] = 0;
                        }
                    }
                }
            }
        }
        chet++;
        return sdvig;
    }

    public int test() {//данная функция проверяет смодет ли пользователь сделать шаг?если нет то пользователь проиграл
        int[][] IdCopy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                IdCopy[i][j] = Id[i][j];
            }
        }//копируем массив с элементами
        int result = 0;//проверяем получится ли сдвинуть
        for (int i = 0; i < 4; i++)//проверяем сдвиг на вверх
        {
            for (int j = 0; j < 4; j++) {
                if (j != 3) {
                    if (IdCopy[i][j] == IdCopy[i][j + 1]) {
                        if (IdCopy[i][j] != 0) {//проверяем можем ли объединить
                            IdCopy[i][j]++;
                            IdCopy[i][j + 1] = 0;
                            result++;
                        }
                    }
                }
                if (0 != j) {
                    if (IdCopy[i][j] != 0) {
                        if (IdCopy[i][j - 1] == 0) {
                            IdCopy[i][j - 1] = IdCopy[i][j];
                            IdCopy[i][j] = 0;
                            result++;
                        }
                    }
                }
            }//конец проверки
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                IdCopy[i][j] = Id[i][j];
            }
        }
        for (int i = 0; i < 4; i++)//проверяем на сдвиг вниз
        {
            for (int j = 3; j > -1; j--) {
                if (j != 0) {
                    if (IdCopy[i][j] == IdCopy[i][j - 1]) {
                        if (IdCopy[i][j] != 0) {//проверяем можем ли объединить
                            IdCopy[i][j]++;
                            IdCopy[i][j - 1] = 0;
                            result++;
                        }
                    }
                }
                if (3 != j) {
                    if (IdCopy[i][j] != 0) {
                        if (IdCopy[i][j + 1] == 0) {
                            result++;//проверяем можно ли сдвинуть
                            IdCopy[i][j + 1] = IdCopy[i][j];
                            IdCopy[i][j] = 0;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                IdCopy[i][j] = Id[i][j];
            }
        }
        for (int j = 0; j < 4; j++)//проверяем можем ли сдвинуть влево
        {
            for (int i = 0; i < 4; i++) {
                if (i != 3) {
                    if (IdCopy[i + 1][j] == IdCopy[i][j]) {
                        if (IdCopy[i][j] != 0) {//проверяем можем ли объединить
                            IdCopy[i][j]++;
                            IdCopy[i + 1][j] = 0;
                            result++;
                        }
                    }
                }
                if (0 != i) {
                    if (IdCopy[i][j] != 0) {
                        if (IdCopy[i - 1][j] == 0) {
                            result++;//проверяем можно ли сдвинуть
                            IdCopy[i - 1][j] = IdCopy[i][j];
                            IdCopy[i][j] = 0;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                IdCopy[i][j] = Id[i][j];
            }
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i > -1; i--) {
                if (i != 0) {
                    if (IdCopy[i - 1][j] == IdCopy[i][j]) {
                        if (IdCopy[i][j] != 0) {//проверяем можем ли объединить
                            IdCopy[i][j]++;
                            IdCopy[i - 1][j] = 0;
                            result++;
                        }
                    }
                }
                if (3 != i) {
                    if (IdCopy[i][j] != 0) {
                        if (IdCopy[i + 1][j] == 0) {
                            result++;//проверяем можно ли сдвинуть
                            IdCopy[i + 1][j] = IdCopy[i][j];
                            IdCopy[i][j] = 0;
                        }
                    }
                }
            }
        }
        return result;
    }

    public int MAX() {
        return max;
    }
}
