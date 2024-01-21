### 반복자 패턴
- 개념
    - 객체의 요소에 순차적으로 접근하고, 요소 간에 순차적으로 이동할 수 있는 행위를 캡슐화하는 패턴
    - 컬렉션(또는 집합체)과 컬렉션 내의 요소를 순회하는 방법을 분리하여 구현합니다.
        - 보통 배열이나 리스트 같은 경우 순서가 연속적인 데이터 집합이기 때문에 간단한 for문을 통해 순회할 수 있지만, 해시, 트리와 같은 컬렉션은 데이터 저장 순서가 정해지지 않고 적재되기 때문에, 각 요소들을 어떤 기준으로 접근해야 할지 기준이 모호함.
        - 복잡하게 얽혀있는 자료 컬렉션들을 순회하는 알고리즘 전략을 정의하는 것이 반복자 패턴

- 구성
    - Aggregate (Iterable) :
        - 여러 개의 객체를 담고 있는 컬렉션 인터페이스
        - 반복자를 생성하는 메서드를 정의(iterator() 메서드)
    - ConcreteAggregate (Concrete Iterable) :
        - 여러 개의 객체를 담고 있는 컬렉션 클래스
        - 해당 컬렉션에 대한 반복자를 생성하는 메서드를 구현
    - Iterator (인터페이스) :
        - 집합체 내의 요소들을 순서대로 검색하기 위한 인터페이스를 제공
            - hasNext() : 순회할 다음 요소가 있는지 확인 (true / false)
            - next() : 요소를 반환하고 다음 요소를 반환할 준비를 하기 위해 커서를 이동시킴
    - ConcreteIterator (클래스) : 반복자 객체
        - ConcreteAggregate가 구현한 메서드로부터 생성되며, ConcreteAggregate 의 컬렉션을 참조하여 객체 순회 및 접근을 담당함
        - 어떤 전략으로 순회할지에 대한 로직을 구체화함

- 사용시기
    - **컬렉션의 탐색 방법을 추상화하고 일반화할 때**
        - 컬렉션의 내부 표현방식이 달라지더라도, 클라이언트는 인터페이스를 통해 순회 및 접근이 가능해짐
    - **컬렉션의 내부 구조에 대한 의존성을 최소화해야 할 때:**
        - 컬렉션의 내부 구조에 상관없이,  일관된 방식으로 요소에 접근할 수 있도록 함
        - 컬렉션의 구현이 변경되어도 클라이언트 코드에 영향이 적어짐
    - **컬렉션을 순회하며 요소에 접근할 필요가 있을 때:**
        - 리스트, 집합, 맵 등의 컬렉션을 순회하며 특정 조건에 따라 처리할 때 활용
- 장점
    - **컬렉션과 클라이언트 코드 간의 결합도 감소:**
        - 반복자 패턴을 사용하면 컬렉션의 내부 구조가 클라이언트 코드에 노출되지 않음
        - 컬렉션의 변경이나 교체에 유연하게 대응 가능함
    - **일관된 인터페이스 제공:**
        - 모든 컬렉션에 대해 일관된 반복자 인터페이스를 제공
        - 클라이언트는 어떤 종류의 컬렉션을 다루더라도 일관된 방식으로 접근할 수 있음
    - **다양한 순회 방법 지원:**
        - 각각의 컬렉션에 맞게 최적화된 반복자를 구현해서 제공할 수 있음
- 단점
    - 복잡도 증가:
        - 컬렉션에 대한 순회 인터페이스를 구현하기 위해 추가적인 클래스와 인터페이스를 도입해야 함
        - 클래스가 늘어나고 복잡도가 증가
    - 성능 이슈 발생 가능:
        - 내부 구조가 복잡하거나 순회 과정이 많은 계산을 필요로 하는 경우 성능 이슈에 유의해야함

    ```java
    //Iterator 인터페이스
    interface BookIterator {
        boolean hasNext();
        String next();
    }
    
    //ConcreteIterator 구현
    class BookListIterator implements BookIterator {
        private List<String> books;
        private int currentIndex;
    
        public BookListIterator(List<String> books) {
            this.books = books;
            this.currentIndex = 0;
        }
    
        @Override
        public boolean hasNext() {
            return currentIndex < books.size();
        }
    
        @Override
        public String next() {
            if (hasNext()) {
                return books.get(currentIndex++);
            }
            return null;
        }
    }
    
    //Aggregate 인터페이스
    interface BookCollection {
        BookIterator createIterator();
    }
    
    //ConcreteAggregate 구현
    class BookShelf implements BookCollection {
        private List<String> books;
    
        public BookShelf() {
            this.books = new ArrayList<>();
        }
    
        public void addBook(String title) {
            books.add(title);
        }
    
        @Override
        public BookIterator createIterator() {
            return new BookListIterator(books);
        }
    }
    
    //Client 코드
    public class IteratorPatternExample {
        public static void main(String[] args) {
            BookShelf bookShelf = new BookShelf();
            bookShelf.addBook("Design Patterns");
            bookShelf.addBook("Clean Code");
            bookShelf.addBook("Java Programming");
    
            // 이터레이터를 통한 순회
            BookIterator iterator = bookShelf.createIterator();
            while (iterator.hasNext()) {
                String book = iterator.next();
                System.out.println("Book: " + book);
            }
        }
    }
    ```