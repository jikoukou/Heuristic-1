import matplotlib.pyplot as plt

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 996, 2424],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 100, Sets = 100')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 996, 2424]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))

plt.savefig('1.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 9984],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 1000, Sets = 100')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 9984]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))
    

plt.savefig('2.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 9999],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 10000, Sets = 100')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 9999]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))

plt.savefig('3.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 8986],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 100, Sets = 1000')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 8986]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))
plt.savefig('4.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 9999],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 1000, Sets = 1000')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 9999]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))
plt.savefig('5.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 9999],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 10000, Sets = 1000')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 9999]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))
    
plt.savefig('6.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 9999],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 100, Sets = 10000')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 9999]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))

plt.savefig('7.png')

plt.figure()
plt.plot([10, 100, 1000, 10000],[9, 99, 999, 9999],'ro-')
plt.xlabel('items')
plt.ylabel('Worst access time')
plt.title('Elements= 1000, Sets = 10000')

for i_x, i_y in zip([10, 100, 1000, 10000], [9, 99, 999, 9999]):
    plt.text(i_x, i_y, '({}, {})'.format(i_x, i_y))
    
    
plt.savefig('8.png')
