
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class server {

    static private Socket clientSocket; //����� ��� �������
    static private ServerSocket server; // �����������
    static int NumberOfClients = 0;//���������� ��������
    static int players = 0;//���������� �������� ������� ��������� � ����
    static Semaphore sem;
    static Object lock = new Object();//������ �������������
    static ArrayList<String> res = new ArrayList();
    static ArrayList<String> nm = new ArrayList();
    static int game = 0;

    /**
     * @param args the command line arguments
     */
    static class Service extends Thread {

        private BufferedReader in; // ����� ������ �� ������
        private BufferedWriter out; // ����� ������ � �����
        Socket Client;
        String word;
        String name;
        boolean wait;

        Service(Socket Client) {
            this.Client = Client;
            try {
                System.out.println("���� ��������");
                out = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(Client.getInputStream()));
            } catch (IOException ex) {
                System.out.println("��������� ������ ��������� ������� in � out");
            }
        }

        @Override
        public void run() {
            try {
                int number;
                word = in.readLine();//��������
                name = word;
                System.out.println("������������ " + name + " �������������");
                while (true) {
                    word = in.readLine();
                    System.out.println("������������ " + name + " �������� " + word);
                    switch (Integer.parseInt(word)) {
                        case 0://������������ ������ ����
                     synchronized (lock) {
                                players--;
                                sem = new Semaphore(players);
                            }
                            break;
                        case 1://������������ ����� ������ � �������
                    try {
                            synchronized (lock) {
                                if (game != 1) {
                                    game = 1;
                                }
                            }
                            //������ ����� � ���� ������
                            number = players;//���������� ���������� �������
                            sem.acquire();
                            while (sem.availablePermits() != 0) {
                                if (number != players) {
                                    sem.acquire();
                                    number = players;
                                }//���� ������� ��� ���������� �� ����������� ������
                            }
                            this.sleep(10);
                            out.write("1\n");
                            out.flush();
                            sem.release();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                        case 2://������������ ����� �������� ���������
                try {
                            //������ ����� � ���� ������
                            word = in.readLine();//�������� ��������� ������
                            synchronized (lock) {
                                res.add(word);
                                nm.add(name);
                            }
                            sem.acquire();
                            while (sem.availablePermits() != 0) {
                            }
                            this.sleep(10);
                            out.write("2\n");
                            out.flush();
                            res.stream().forEach(s -> {
                                try {
                                    out.write(s + "\n");
                                    out.flush();
                                } catch (IOException ex) {
                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            out.write("0\n");
                            out.flush();
                            nm.stream().forEach(s -> {
                                try {
                                    out.write(s + "\n");
                                    out.flush();
                                } catch (IOException ex) {
                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            out.write("0\n");
                            out.flush();
                            if (sem.availablePermits() == players - 1) {
                                synchronized (lock) {
                                    if (game != 0) {
                                        game = 0;
                                    }
                                    if (res.size() != 0) {
                                        res = new ArrayList();
                                        nm = new ArrayList();
                                    }
                                }
                            }
                            sem.release();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                        case 3://������������ ����� ������ ������
                            while (game != 0) {
                                try {
                                    this.sleep(10);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            synchronized (lock) {
                                players++;
                                sem = new Semaphore(players);
                            }
                            out.write("3\n");
                            out.flush();
                            break;
                    }
                }
            } catch (IOException ex) {
                System.out.println("������ ����������");
                NumberOfClients--;
                players--;
                sem = new Semaphore(players);
                System.out.println("���������� ��������:" + NumberOfClients);
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("������ �������");
        try {
            server = new ServerSocket(4004); // ����������� ������������ ���� 4004
            while (true) {
                clientSocket = server.accept(); // accept() ����� ����� ����
                NumberOfClients++;
                sem = new Semaphore(NumberOfClients);
                players++;
                new Service(clientSocket).start();
                System.out.println("���������� ��������:" + NumberOfClients);
            }
        } catch (IOException ex) {
            System.out.println("��������� ������ ���������� �������");
        }
    }
}
