import java.util.ArrayList;
import java.util.List;

public class Task1To3 {
    //проверка на палиндром
    //задание 1
    public boolean solve(int k) {
        double buff = k;
        List<Integer> list = new ArrayList<>();
        if (buff < 0) {
            buff *= -1;
        }
        while (buff > 1) {
            list.add((int) (buff%10));
            buff /= 10;
        }
        for (int i = 0, j = list.size()-1; i < list.size()/2; i++,j--) {
            if (list.get(i)!=list.get(j)){
                return false;
            }
        }
        return true;
    }


    //попытка разворота матрици в виде одномерного массива
    //работает плохо
    public void solve3(int[] a){
        for (int i = 0; i < (a.length - 2) / 2; i++) {
            int tmp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length-1-i]=tmp;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
            if (i==2 || i== 5){
                System.out.println();
            }
        }
    }

    //разворот матрици с помощью дополнительного локального массива
    public void solve(int[][] m ){
        int[][] res = new int[m[0].length][m.length];
        int rI=0;
        int rJ=m.length-1;
        for (int[] ints : m) {
            for (int anInt : ints) {
                res[rI++][rJ]=anInt;
            }
            rI=0;
            rJ--;
        }
    }

//
//    public void solve2(int[][] m ){
//        for (int i = 0; i < (m.length - 2) / 2; i++) {
//            for (int j = 0; j < (m[0].length - 2) / 2; j++) {
//
//            }
//        }
//    }



    //Задание 3
    public int solve(int[] k){
        int result = 0;
        int[] buff = k;
        int max = 1;                                                // для поиска самого большого числа, а = 1 потому что нужно зайти в цикл
        for (int m = 0; m < max; m++) {                             //m - количество слоев ли высота сбора,
            for (int i = 0;i < buff.length - 1;i++) {               //проходимся по всем элементам массива
                int mid = 0;                                        //локальная переменная счетчик для учета потенциального количества ячеек в которых может находиться собранная вода
                max = Math.max(buff[i], buff[i + 1]);               //находим максимальный элемент
                if (buff[i] >= 1) {                                 //если этот элемент является бетонным блоком
                    buff[i]-=1;                                     //сразу делаем срез для него
                    for (int j = i + 1; j < buff.length; j++) {     //и запускаем новый цикл для поиска ячеек в которых потенциально может быть вода
                        if (buff[j] == 0) {                         //если уровень равен 0
                            mid++;                                  //то присваиваем значению единицу так как в этом месте может быть ячейка, но не факт
                        }
                        if (buff[j] > 0) {                          //проверяем есть ли бетонный блок, если есть, то
                            result += mid;                          //присваиваем результату значение наших ячеек
                            i = j;                                  //передвигаем курсор на позицию нагого бетонного блока
                            if (i==buff.length-1){                  //если этот бетонный блок является последним
                                buff[i]--;                          //то делаем срез
                            }
                            i--;                                    //и уменьшаем наш курсор на единицу так как цикл его увеличит и вернет на туже позицию, что б не пропускать елементы
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

}
