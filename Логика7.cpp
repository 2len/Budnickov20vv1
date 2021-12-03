#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <locale.h>
#include <queue>
using namespace std;

int* vis;
int num;
int** m;
void BFSD(int num);

struct list
{
	int field; // поле данных
	struct list* ptr; // указатель на следующий элемент
};

void BFSD(int c) {
	int s;
	queue<int> q;
	vis[c] = 0;
	q.push(c);
	while (!q.empty()) {
		s = q.front();
		q.pop();
		printf("%4i", s);
		for (int i = 0; i < num; i++) {
			if (m[s][i] > 0 && vis[i] > (m[s][i] + vis[s])) {
				q.push(i);
				vis[i] = m[s][i] + vis[s];
			}
		}
	}
	printf("\nРасстояние до вершин: ");
	for (int i = 0; i < num; i++) {
		printf("%4i", vis[i]);
	}

}

int main()
{
	setlocale(LC_ALL, "Rus");
	int generate;
	int c;
	printf("Введите размер массива: ");
	scanf("%i", &num);
	m = new int* [num];
	for (int i = 0; i < num; i++) {
		m[i] = new int[num];
	}
	srand(time(NULL));
	for (int i = 0; i < num; i++) {
		for (int j = 0; j < num; j++) {
			if (j == i) {
				m[i][j] = 0;
			}
			else if (j > i) {
				generate = rand() % 100;
				if (generate >= 50) {
					m[i][j] = rand() % 11;
				}
				else {
					m[i][j] = 0;
				}
			}
			else {
				m[i][j] = m[j][i];
			}
		}
	}
	//вывод массива
	printf("Массив:");
	for (int i = 0; i < num; i++) {
		printf("\n");
		for (int j = 0; j < num; j++) {
			printf("%4i", m[i][j]);
		}
	}
	vis = new int[num];
	for (int i = 0; i < num; i++) {
		vis[i] = 10000;
	}
	printf("\nВведите вершину начала обхода: ");
	scanf("%i", &c);
	printf("\n");
	printf("\n");
	BFSD(c);
}