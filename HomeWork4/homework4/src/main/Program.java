package main;
/*
Java Core (��������)
���� 4. ��������� ����������
������ 1:

1 �������� �����, �� ���� �������� ������� ��������� ��������� ������ �������� 4�4. ���
������ ������� ������� ������� ���������� ������� ���������� MyArraySizeException.

2 ����� ����� ������ �������� �� ���� ��������� �������, ������������� � int �
��������������. ���� � �����-�� �������� ������� �������������� �� ������� (��������, �
������ ����� ������ ��� ����� ������ �����), ������ ���� ������� ����������
MyArrayDataException � ������������, � ����� ������ ������ ����� �������� ������.

3 � ������ main() ������� ���������� �����, ���������� ��������� ����������
MyArraySizeException � MyArrayDataException � ������� ��������� �������.
 */

public class Program {
    public static void main(String[] args) {
        String[][] array = {
                { "1","2","3","4"},
                { "5","6","7","8"},
                { "9","10","11","12"},
                {"13","14","15","16"},

        };
        try {
            System.out.printf("����� ��������� ������� �����: %s",Method(array));
        } catch (MyArraySizeException ex) {
            System.out.println(ex.getMessage());
//            ex.printStackTrace();
        }
    }

    /**
     * ������ �������� ����� ���� ���������� �������
     * @param array ������ 4�4
     * @return ����� ���������
     * @throws MyArraySizeException ���������� ����������� �������
     */
    public static int Method(String[][] array) throws MyArraySizeException {
        if (array.length!=4)
            throw new MyArraySizeException("���������� ����� ������� �� ����� 4!");

        for (int i = 0; i < array.length; i++) {
            if (array[i].length!=4)
                throw new MyArraySizeException("����������� "+(i+1)+"-� ������ ������� �� ����� 4!");
        }

        int result=0;
        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch(NumberFormatException ex) {
                    throw new MyArrayDataException("������������ ������� � ������ ",i,j);
                }
            }

        }
        return result;
    }
}
