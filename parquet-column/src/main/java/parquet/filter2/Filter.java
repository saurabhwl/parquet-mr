package parquet.filter2;

import parquet.filter2.FilterPredicates.And;
import parquet.filter2.FilterPredicates.BinaryUserDefined;
import parquet.filter2.FilterPredicates.Column;
import parquet.filter2.FilterPredicates.DoubleUserDefined;
import parquet.filter2.FilterPredicates.Eq;
import parquet.filter2.FilterPredicates.FloatUserDefined;
import parquet.filter2.FilterPredicates.Gt;
import parquet.filter2.FilterPredicates.GtEq;
import parquet.filter2.FilterPredicates.IntUserDefined;
import parquet.filter2.FilterPredicates.LongUserDefined;
import parquet.filter2.FilterPredicates.Lt;
import parquet.filter2.FilterPredicates.LtEq;
import parquet.filter2.FilterPredicates.Not;
import parquet.filter2.FilterPredicates.NotEq;
import parquet.filter2.FilterPredicates.Or;
import parquet.filter2.FilterPredicates.StringUserDefined;
import parquet.filter2.UserDefinedPredicates.BinaryUserDefinedPredicate;
import parquet.filter2.UserDefinedPredicates.DoubleUserDefinedPredicate;
import parquet.filter2.UserDefinedPredicates.FloatUserDefinedPredicate;
import parquet.filter2.UserDefinedPredicates.IntUserDefinedPredicate;
import parquet.filter2.UserDefinedPredicates.LongUserDefinedPredicate;
import parquet.filter2.UserDefinedPredicates.StringUserDefinedPredicate;

/**
 * The Filter API is expressed through these static methods.
 *
 * Example usage:
 * {@code
 *
 *   Column<Integer> foo = column("foo");
 *   Column<Double> bar = column("x.y.bar");
 *
 *   // foo == 10 || bar <= 17.0
 *   FilterPredicate pred = or(eq(foo, 10), ltEq(bar, 17.0));
 *
 * }
 */
public final class Filter {
  private Filter() { }

  public static <T> Column<T> column(String columnPath) {
    return new Column<T>(columnPath);
  }

  public static <T> Eq<T> eq(Column<T> column, T value) {
    return new Eq<T>(column, value);
  }

  public static <T> NotEq<T> notEq(Column<T> column, T value) {
    return new NotEq<T>(column, value);
  }

  public static <T> Lt<T> lt(Column<T> column, T value) {
    return new Lt<T>(column, value);
  }

  public static <T> LtEq<T> ltEq(Column<T> column, T value) {
    return new LtEq<T>(column, value);
  }

  public static <T> Gt<T> gt(Column<T> column, T value) {
    return new Gt<T>(column, value);
  }

  public static <T> GtEq<T> gtEq(Column<T> column, T value) {
    return new GtEq<T>(column, value);
  }

  public static <T extends IntUserDefinedPredicate> IntUserDefined intUserDefined(Column<Integer> column, Class<T> clazz) {
    return new IntUserDefined<T>(column, clazz);
  }

  public static <T extends LongUserDefinedPredicate> LongUserDefined longUserDefined(Column<Long> column, Class<T> clazz) {
    return new LongUserDefined<T>(column, clazz);
  }

  public static <T extends FloatUserDefinedPredicate> FloatUserDefined FloatUserDefined(Column<Float> column, Class<T> clazz) {
    return new FloatUserDefined<T>(column, clazz);
  }

  public static <T extends DoubleUserDefinedPredicate> DoubleUserDefined DoubleUserDefined(Column<Double> column, Class<T> clazz) {
    return new DoubleUserDefined<T>(column, clazz);
  }

  public static <T extends BinaryUserDefinedPredicate> BinaryUserDefined BinaryUserDefined(Column<byte[]> column, Class<T> clazz) {
    return new BinaryUserDefined<T>(column, clazz);
  }

  public static <T extends StringUserDefinedPredicate> StringUserDefined StringUserDefined(Column<String> column, Class<T> clazz) {
    return new StringUserDefined<T>(column, clazz);
  }

  public static FilterPredicate and(FilterPredicate left, FilterPredicate right) {
    return new And(left, right);
  }

  public static FilterPredicate or(FilterPredicate left, FilterPredicate right) {
    return new Or(left, right);
  }

  public static FilterPredicate not(FilterPredicate predicate) {
    return new Not(predicate);
  }

}