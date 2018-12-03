package mainpackage;

import java.util.ArrayList;

public class Finder extends Thread {
    private ArrayList<Integer> primes = new ArrayList<Integer>(1000000);
    
    public Finder() {
        primes.add(3);
        primes.add(5);
    }
    
    public void run() {
        int test = 7;
        int root = 3;
        int rootSquared = 9;
        int index;
        int primeAtIndex;
        boolean isPrime;
        
        while (!this.isInterrupted()) {
            if (rootSquared < test) {
                root++;
                rootSquared = root * root;
            }
            
            isPrime = true;
            index = 0;
            primeAtIndex = 3;
            
            while (primeAtIndex <= root) {
                if (test % primeAtIndex == 0) {
                    isPrime = false;
                    break;
                } else {
                    index++;
                    primeAtIndex = primes.get(index);
                }
            }
            
            if (isPrime) {
                synchronized (this) {
                    primes.add(test);
                }
            }
            
            test += 2;
        }
    }
    
    public synchronized int get(int index) {
        return primes.get(index);
    }
    
    public synchronized int size() {
        return primes.size();
    }
    
    public synchronized void printAll() {
        for (int i = 0, max = primes.size(); i < max; i++) {
            System.out.println((i + 2) + ": " + primes.get(i));
        }
    }
}