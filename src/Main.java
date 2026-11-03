public class Main {

    public static void main(String[] args) {
        FileSystemComponent report = new DocumentFile("report.pdf", 500);
        FileSystemComponent emptyFolder = new Folder("empty");

        Folder root = new Folder("root");
        Folder documents = new Folder("documents");
        Folder photos = new Folder("photos");
        Folder vacation = new Folder("vacation");

        root.add(report);
        root.add(documents);
        root.add(photos);
        root.add(emptyFolder);

        documents.add(new DocumentFile("contract.docx", 120));
        documents.add(new DocumentFile("budget.xlsx", 80));

        photos.add(new DocumentFile("photo.png", 1500));
        photos.add(vacation);

        vacation.add(new DocumentFile("beach.jpg", 2300));
        vacation.add(new DocumentFile("mountain.jpg", 1800));

        System.out.println("=== Sizes ===");
        printSize(report);
        printSize(emptyFolder);
        printSize(vacation);
        printSize(photos);
        printSize(root);

        System.out.println("\n=== Hierarchy ===");
        root.display("");

        System.out.println("\n=== After removing the photos folder ===");
        root.remove(photos);
        root.display("");
    }

    private static void printSize(FileSystemComponent component) {
        System.out.println(component.getName() + ": " + component.getSize() + " KB");
    }
}
