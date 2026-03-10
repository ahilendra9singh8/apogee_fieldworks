If we can sort an array with any one sorting technique, then why do we have many different ones like Selection, Bubble, Insertion, Merge, and Quick?
The answer comes down to differences in efficiency, simplicity, and use case. Here’s a breakdown:

Selection, Bubble, Insertion inke baad merge sort use krte h to vh time complexity kam kr deta h.
aur merge sort ke baad quick sort use krte h to vh space compaxity kam kr deta 

niche chart me sirf best case aur space dekhe.



| Algorithm   | Best Case  | Average Case | Worst Case | Space    |
| ----------- | ---------- | ------------ | ---------- | -------- |
| Bubble Sort | O(n)       | O(n²)        | O(n²)      | O(1)     |
| Selection   | O(n²)      | O(n²)        | O(n²)      | O(1)     |
| Insertion   | O(n)       | O(n²)        | O(n²)      | O(1)     |
| Merge Sort  | O(n log n) | O(n log n)   | O(n log n) | O(n)     |
| Quick Sort  | O(n log n) | O(n log n)   | O(n²)      | O(log n) |


So:
Merge Sort is great for consistent performance, even for large arrays.
Quick Sort is often fastest in practice, though it has a bad worst-case.
Bubble, Selection, and Insertion are slower, but simpler for learning or small datasets.

📦 2. Memory Usage
Some algorithms like Merge Sort use extra memory.
Others like Quick Sort, Bubble, Insertion are in-place (they don't need extra arrays).

