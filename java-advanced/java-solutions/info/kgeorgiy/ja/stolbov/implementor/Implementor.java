package info.kgeorgiy.ja.stolbov.implementor;

import info.kgeorgiy.java.advanced.implementor.Impler;
import info.kgeorgiy.java.advanced.implementor.ImplerException;
import info.kgeorgiy.java.advanced.implementor.tools.JarImpler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Class implementing {@link Impler} and {@link JarImpler}.
 * Creates new class implementing given interface.
 *
 * @author Specia1Forces
 */
public class Implementor implements JarImpler {
    final static Charset STANDARD_CHARSETS = StandardCharsets.UTF_8;

    /**
     * Calling {@link Implementor#implement(Class, Path)} or {@link Implementor#implementJar(Class, Path)}
     * depends on number of arguments
     *
     * @param args name of interface will be implemented
     */
    public static void main(String[] args) {

        if (args == null || args.length > 3 || args[0] == null || args[1] == null) {
            System.err.println("Incorrect input. Arguments can't be null or less than 2.");
            return;
        }
        Implementor impl = new Implementor();
        if (args[0].equals("--jar")) {
            if (args[2] != null) {
                try {
                    impl.implementJar(args[1].getClass(), Path.of(args[2]));
                } catch (ImplerException e) {
                    System.err.println(e.getMessage());
                }
            } else System.err.println("Incorrect input. Arguments can't be null.");
        } else {
            try {
                impl.implement(args[0].getClass(), Path.of(args[1]));
            } catch (ImplerException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * The standard constructor
     */
    public Implementor() {

    }

    /**
     * Implements the specified interface and writes the implementation to the specified path.
     *
     * @param token the interface class to implement
     * @param root  the root directory where the implementation will be saved
     * @throws ImplerException if the input is invalid or if an error occurs during writing
     */
    @Override
    public void implement(Class<?> token, Path root) throws ImplerException {
        if (root == null || token == null) {
            throw new ImplerException("Invalid input. Arguments can't be null.");
        }
        if (!token.isInterface()) {
            throw new ImplerException("You can implement only interfaces.");
        }
        if (Modifier.isPrivate(token.getModifiers())) {
            throw new ImplerException("Interface is private.");
        }

        Path filePath = getCorrectFilePath(token, root);
        createDirs(filePath);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath, STANDARD_CHARSETS)) {
            writePackage(token, bufferedWriter);
            writeClassHeader(token, bufferedWriter);
            writeMethods(token, bufferedWriter);
            bufferedWriter.write("}");
        } catch (IOException ex) {
            throw new ImplerException("There were errors with the file: " + ex.getMessage());
        }
    }

    /**
     * Creates the necessary directories for the specified file path.
     *
     * @param file the path of the file for which directories should be created
     * @throws ImplerException if an error occurs during directory creation
     */
    private void createDirs(Path file) throws ImplerException {
        try {
            Path parent = file.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            throw new ImplerException("Cannot creating directories." + " " + e.getMessage());
        }
    }

    /**
     * Constructs the correct file path for the implementation based on the interface and root path.
     *
     * @param token the interface class to implement
     * @param root  the root directory where the implementation will be saved
     * @return the constructed file path for the implementation
     */
    private Path getCorrectFilePath(Class<?> token, Path root) {
        String packagePath = token.getPackageName().replace(".", File.separator);

        String className = token.getSimpleName() + "impl";

        return root.resolve(packagePath).resolve(className + ".java");
    }

    /**
     * Writes the package declaration to the provided BufferedWriter.
     *
     * @param token          the interface class whose package will be written
     * @param bufferedWriter the BufferedWriter to write the package declaration
     * @throws IOException if an error occurs during writing
     */
    private void writePackage(Class<?> token, BufferedWriter bufferedWriter) throws IOException {
        String pack = token.getPackageName();
        if (!pack.isEmpty()) {
            bufferedWriter.write("package ");
            bufferedWriter.write(pack);
            bufferedWriter.write(";");
            bufferedWriter.newLine();
        }


    }

    /**
     * Writes the class header for the implementation of the specified interface.
     * The header includes the class declaration and the interface it implements.
     *
     * @param token          the interface class whose implementation is being generated
     * @param bufferedWriter the BufferedWriter to which the class header will be written
     * @throws IOException if an error occurs during writing
     */
    private void writeClassHeader(Class<?> token, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("public class ");
        bufferedWriter.write(token.getSimpleName() + "Impl ");
        bufferedWriter.write("implements " + token.getCanonicalName() + " {");
        bufferedWriter.newLine();
    }

    /**
     * Writes the methods for the implementation of the specified interface.
     * This method retrieves all methods from the interface and writes their headers
     * and return statements to the provided BufferedWriter.
     *
     * @param token          the interface class whose methods are being implemented
     * @param bufferedWriter the BufferedWriter to which the method implementations will be written
     * @throws IOException if an error occurs during writing
     */
    private void writeMethods(Class<?> token, BufferedWriter bufferedWriter) throws IOException {
        Method[] method = token.getMethods();
        for (Method value : method) {
            writeHeaderMethod(value, bufferedWriter);
            writeReturn(value, bufferedWriter);
        }
    }

    /**
     * Writes the header for a given method, including its access modifier,
     * return type, name, and parameters.
     *
     * @param method         the method to be implemented
     * @param bufferedWriter the BufferedWriter to which the method header will be written
     * @throws IOException if an error occurs during writing
     */
    private void writeHeaderMethod(Method method, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write("public ");
        bufferedWriter.write(method.getReturnType().getCanonicalName() + " " + method.getName() + " ( ");
        Parameter[] parameters = method.getParameters();
        for (int j = 0; j < method.getParameterCount(); j++) {
            bufferedWriter.write(String.valueOf(parameters[j].getType().getCanonicalName()));
            bufferedWriter.write(" arg" + j + " ");
            if (j + 1 != method.getParameterCount()) {
                bufferedWriter.write(", ");
            }
        }
        bufferedWriter.write(") {");
        bufferedWriter.newLine();
    }

    /**
     * Writes a return statement for a given method based on its return type.
     * If the return type is a primitive type, it writes a default value;
     * if it is an object type, it writes 'null'; if it is void, nothing is written.
     *
     * @param method         the method for which the return statement is being generated
     * @param bufferedWriter the BufferedWriter to which the return statement will be written
     * @throws IOException if an error occurs during writing
     */
    private void writeReturn(Method method, BufferedWriter bufferedWriter) throws IOException {
        String returnType;
        if (!method.getReturnType().isPrimitive()) {
            returnType = "null";
        } else if (method.getReturnType().equals(boolean.class)) {
            returnType = "false";
        } else if (method.getReturnType().equals(void.class)) {
            returnType = "";
        } else {
            returnType = "0";
        }
        bufferedWriter.write("return " + returnType + ";}");
        bufferedWriter.newLine();
    }

    /**
     * Generates an implementation of the specified interface, compiles it,
     * and packages it into a JAR file.
     *
     * @param token   the interface class whose implementation is being generated
     * @param jarFile the path to the JAR file to be created
     * @throws ImplerException if an error occurs during the implementation or compilation process
     */
    @Override
    public void implementJar(Class<?> token, Path jarFile) throws ImplerException {
        Path path = Paths.get("");

        implement(token, path);

        String packagePath = token.getPackageName();
        String normalizedPath = packagePath.replace(".", File.separator);
        String pathOfImplFile = normalizedPath + File.separator + token.getSimpleName() + "Impl";

        try {
            final String classpath = File.pathSeparator + getClassPath(token);
            String[] args = {pathOfImplFile + ".java",
                    "-cp",
                    classpath,
                    "-encoding",
                    "UTF-8"
            };

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            if (compiler == null) {
                throw new ImplerException(
                        "Don't include tools.jar to classpath");
            }

            int exitCode = compiler.run(null, null, null, args);
            if (exitCode != 0) {
                throw new ImplerException("Can't compile file");
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (OutputStream fileOutputStream = Files.newOutputStream(jarFile);
             ZipOutputStream zipOutputStream = new JarOutputStream(fileOutputStream)) {

            String classFilePath = pathOfImplFile.replace(File.separator, "/") + ".class";

            ZipEntry zipEntry = new ZipEntry(classFilePath);
            zipOutputStream.putNextEntry(zipEntry);

            Path sourcePath = Paths.get(classFilePath);
            Files.copy(sourcePath, zipOutputStream);

        } catch (IOException e) {
            throw new RuntimeException("Error when creating a JAR file", e);
        }


    }

    /**
     * Retrieves the classpath for the specified class.
     *
     * @param token the class whose classpath is to be retrieved
     * @return the path to the classpath as a Path object
     * @throws URISyntaxException if the URI syntax is incorrect
     */
    private static Path getClassPath(Class<?> token) throws URISyntaxException {
        return Path.of(token.getProtectionDomain().getCodeSource().getLocation().toURI());
    }
}

//note -- не работают ссылки на интерфейсы info.kgeorgiy.java.advanced.implementor.Impler и info.kgeorgiy.java.advanced.implementor.tools.JarImpler

//note -- также, писать скрипты к другим hw необязательно, они не проверяются
