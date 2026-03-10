💾 JPA Relationships & Mappings — Complete Notes
🔹 What is Mapping?

Mapping means connecting Java objects (entities) to database tables and showing how they are related (One-to-One, One-to-Many, etc).

🔹 1️⃣ One-To-One (@OneToOne)
👉 Meaning:

One record in one table is linked to exactly one record in another table.
Example: One Person has one Passport.

👉 Example:
@Entity
class Person {
    @Id
    private int id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;
}

@Entity
class Passport {
    @Id
    private int id;
    private String passportNumber;

    @OneToOne(mappedBy = "passport")
    private Person person;
}

👉 Key Points:

@JoinColumn → foreign key column in Person table.

mappedBy → used on the inverse side (Passport).

Default fetch = EAGER.

🔹 2️⃣ One-To-Many & Many-To-One
👉 Meaning:

One Category has many Products.

Each Product belongs to one Category.

👉 Example:
@Entity
class Category {
    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;
}

@Entity
class Product {
    @Id
    private int id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}

👉 Key Points:

mappedBy = "category" → tells that Product owns the relationship.

Foreign key is stored in product table (category_id).

OneToMany default fetch = LAZY.

ManyToOne default fetch = EAGER.

🔹 3️⃣ Many-To-Many (@ManyToMany)
👉 Meaning:

Many records in one table relate to many in another table.
Example: Many Students can enroll in many Courses.

👉 Example:
@Entity
class Student {
    @Id
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses;
}

@Entity
class Course {
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}

👉 Key Points:

A join table (student_course) is created automatically.

@JoinTable → defines join table and column names.

Default fetch = LAZY.

⚙️ Common Attributes in Relationships
Attribute	                              Meaning
mappedBy	                       Defines the inverse (non-owning) side of relationship.
cascade	                           Defines how parent’s operations affect children.
fetch	                           Controls when related data is loaded (LAZY or EAGER).
orphanRemoval	                   Removes child record if it’s deleted from parent collection.
@JoinColumn	                       Specifies the foreign key column name.
@JoinTable	                       Used in @ManyToMany to define a middle (join) table.


🔹 Cascade Types
CascadeType	                                             Description
ALL	                                               Applies all operations (persist, merge, remove, etc.)
PERSIST	                                           Child is saved when parent is saved
MERGE	                                           Child is updated when parent is updated
REMOVE	                                           Child is deleted when parent is deleted
REFRESH	                                           Child is refreshed when parent is refreshed
DETACH	                                           Child is detached when parent is detached

👉 Example:
@OneToMany(cascade = CascadeType.ALL)
private Set<Product> products;

🔹 Fetch Types
Fetch Type	                             Description	                                                 Use
EAGER	                            Related entity is loaded immediately	                      Use when always needed
LAZY	                            Related entity is loaded only when accessed	                  Better for performance



👉 Default Fetch Types:
Mapping	                            Default Fetch Type
@OneToOne	                            EAGER
@ManyToOne	                            EAGER
@OneToMany	                            LAZY
@ManyToMany	                            LAZY



🔹 Orphan Removal

If you remove a child object from the parent’s collection, it will also be deleted from the database.

👉 Example:
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderItem> items;

🧠 Quick Relationship Summary
Relationship	     Example	             Foreign Key	                     Fetch Default	         Notes
OneToOne	       Person ↔ Passport	       One side	                             EAGER	           One object linked to one
OneToMany	       Category → Products	   In “many” table	                         LAZY	           Parent → multiple children
ManyToOne	       Product → Category	   In Product table	                         EAGER	           Many children → one parent
ManyToMany	       Students ↔ Courses	       Join Table	                         LAZY	           Many to many link
💡 Short Interview Tips

✅ mappedBy → used on the non-owning side.
✅ JoinColumn → creates a foreign key.
✅ CascadeType.ALL → propagates all operations.
✅ LAZY fetch → improves performance.
✅ EAGER fetch → loads data immediately.
✅ OrphanRemoval → removes child automatically.
✅ JoinTable → used in Many-to-Many.