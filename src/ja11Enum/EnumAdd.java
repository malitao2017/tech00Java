//package ja11Enum;
//
//import java.lang.reflect.AccessibleObject;
//import java.lang.reflect.Array;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class EnumAdd {
//
//	/**
//	 * 一、方法 void addEnum(Class<T> enumType, String enumName)，用于增加一个枚举类型：
//	 * 基于反射，把枚举类的属性列表全部取出来，增加一个新的枚举类型以后再放回去。上面的第3、5、6步下面分别说明。
//     * Add an enum instance to the enum class given as argument
//     *
//     * @param the type of the enum (implicit)
//     * @param enumType the class of the enum to be modified
//     * @param enumName the name of the new enum instance to be added to the class.
//     */
//    @SuppressWarnings("unchecked")
//    public static <T extends Enum<?>> void addEnum(Class<T> enumType, String enumName) {
// 
//        // 0. Sanity checks
//        if (!Enum.class.isAssignableFrom(enumType)) {
//            throw new RuntimeException("class " + enumType + " is not an instance of Enum");
//        }
//        // 1. Lookup "$VALUES" holder in enum class and get previous enum instances
//        Field valuesField = null;
//        Field[] fields = TestEnum.class.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.getName().contains("$VALUES")) {
//                valuesField = field;
//                break;
//            }
//        }
//        AccessibleObject.setAccessible(new Field[] { valuesField }, true);
// 
//        try {
// 
//            // 2. Copy it
//            T[] previousValues = (T[]) valuesField.get(enumType);
//            List values = new ArrayList(Arrays.asList(previousValues));
// 
//            // 3. build new enum
//            T newValue = (T) makeEnum(enumType, // The target enum class
//                    enumName, // THE NEW ENUM INSTANCE TO BE DYNAMICALLY ADDED
//                    values.size(),
//                    new Class<><[] {}, // can be used to pass values to the enum constuctor
//                    new Object[] {}); // can be used to pass values to the enum constuctor
// 
//            // 4. add new value
//            values.add(newValue);
// 
//            // 5. Set new values field
//            setFailsafeFieldValue(valuesField, null,
//                    values.toArray((T[]) Array.newInstance(enumType, 0)));
// 
//            // 6. Clean enum cache
//            cleanEnumCache(enumType);
// 
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }
//    /**
//     * 二、在其中使用到了 makeEnum(Class<?> enumClass, String value, int ordinal, Class<?>[] additionalTypes, Object[] additionalValues) 这样一个方法：
//     * 这是真正使用newInstance方法来创建新的枚举对象的方法，注意其中的构造器参数类型，第一个是String，第二个是int，这是需要符合enum内部实现的。
//     */
//	private static Object makeEnum(Class<?> enumClass, String value, int ordinal, Class<?>[] additionalTypes,Object[] additionalValues) throws Exception {
//		Object[] parms = new Object[additionalValues.length + 2];
//		parms[0] = value;
//		parms[1] = Integer.valueOf(ordinal);
//		System.arraycopy(additionalValues, 0, parms, 2, additionalValues.length);
//		return enumClass.cast(getConstructorAccessor(enumClass, additionalTypes).newInstance(parms));
//	}
//	
//	private static ConstructorAccessor getConstructorAccessor(Class<?> enumClass, Class<?>[] additionalParameterTypes)
//			throws NoSuchMethodException {
//		Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 2];
//		parameterTypes[0] = String.class;
//		parameterTypes[1] = int.class;
//		System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);
//		return reflectionFactory.newConstructorAccessor(enumClass.getDeclaredConstructor(parameterTypes));
//	}
//	
//	/**
//	 * 三、setFailsafeFieldValue(Field field, Object target, Object value) throws NoSuchFieldException, IllegalAccessException，这个修改方法modifier并设值的方法：
//	 */
//	private static void setFailsafeFieldValue(Field field, Object target, Object value)
//			throws NoSuchFieldException, IllegalAccessException {
//
//		// let's make the field accessible
//		field.setAccessible(true);
//
//		// next we change the modifier in the Field instance to
//		// not be final anymore, thus tricking reflection into
//		// letting us modify the static final field
//		Field modifiersField = Field.class.getDeclaredField("modifiers");
//		modifiersField.setAccessible(true);
//		int modifiers = modifiersField.getInt(field);
//
//		// blank out the final bit in the modifiers int
//		modifiers &= ~Modifier.FINAL;
//		modifiersField.setInt(field, modifiers);
//
//		FieldAccessor fa = reflectionFactory.newFieldAccessor(field, false);
//		fa.set(target, value);
//	}
//
//
//	/**
//	 * 四、清除枚举缓存：cleanEnumCache(Class<?> enumClass) throws NoSuchFieldException, IllegalAccessException：
//	 */
//	private static void cleanEnumCache(Class<?> enumClass) throws NoSuchFieldException, IllegalAccessException {
//		blankField(enumClass, "enumConstantDirectory"); // Sun (Oracle?!?) JDK
//														// 1.5/6
//		blankField(enumClass, "enumConstants"); // IBM JDK
//	}
//
//	private static void blankField(Class<?> enumClass, String fieldName)
//			throws NoSuchFieldException, IllegalAccessException {
//		for (Field field : Class.class.getDeclaredFields()) {
//			if (field.getName().contains(fieldName)) {
//				AccessibleObject.setAccessible(new Field[] { field }, true);
//				setFailsafeFieldValue(field, enumClass, null);
//				break;
//			}
//		}
//	}
//
//
//	/**
//	 * 	最近在使用一个内部框架的时候，希望能够在运行时指定枚举类型，却发现这是一件挺麻烦的事情（不找别的替代方式，就是要动态增加enum的类型），方法也不正统，不过作为有趣的尝试，研究研究也无妨，下面的内容主要来自于《Java – create enum instances dynamically》这样一篇文章。在一切开始前，如果你想问，为什么非要增加/改变enum类型？其实这是一个非常好的问题，多数情况下这是应对被避免的，但是这不在今天我的讨论范围内。
//	 * @author malitao
//	 *
//	 */
//	private static enum TestEnum {
//		a, b, c;
//	};
//
//	public static void main(String[] args) {
//
//		// Dynamically add 3 new enum instances d, e, f to TestEnum
//		addEnum(TestEnum.class, "d");
//		addEnum(TestEnum.class, "e");
//		addEnum(TestEnum.class, "f");
//
//		// Run a few tests just to show it works OK.
//		System.out.println(Arrays.deepToString(TestEnum.values()));
//		// Shows : [a, b, c, d, e, f]
//	}
//}
