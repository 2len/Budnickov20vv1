#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <locale.h>
#include <queue>
using namespace std;

int* vis;
int num;
int** m;
void BFS(int num);

struct list
{
	int field; // поле данных
	struct list* ptr; // указатель на следующий элемент
};

void BFS(int c) {
	int s;
	queue<int> q;
	vis[c] = 1;
	q.push(c);
	while (!q.empty()) {
		s = q.front();
		q.pop();
		printf("%4i", s);
		for (int i = 0; i < num; i++) {
			if (m[s][i] == 1 && vis[i] == 0) {
				q.push(i);
				vis[i] = 1;
			}
		}
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
				if (generate >= 30) {
					m[i][j] = 1;
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
		vis[i] = 0;
	}
	printf("\nВведите вершину начала обхода: ");
	scanf("%i", &c);
	printf("\n");
	printf("\n");
	BFS(c);
}