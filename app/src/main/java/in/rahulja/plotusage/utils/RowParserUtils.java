package in.rahulja.plotusage.utils;

import in.rahulja.plotusage.datatypes.Battery;
import in.rahulja.plotusage.datatypes.BluetoothStatus;
import in.rahulja.plotusage.datatypes.Brightness;
import in.rahulja.plotusage.datatypes.CellSignal;
import in.rahulja.plotusage.datatypes.CpuFrequency;
import in.rahulja.plotusage.datatypes.FreeMemory;
import in.rahulja.plotusage.datatypes.GpsStatus;
import in.rahulja.plotusage.datatypes.RowDateTime;
import in.rahulja.plotusage.datatypes.RowLocation;
import in.rahulja.plotusage.datatypes.WifiStatus;
import in.rahulja.plotusage.interfaces.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import static in.rahulja.plotusage.constants.CommonConstants.COMMA;
import static in.rahulja.plotusage.constants.CommonConstants.PERCENT_LOC;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RowParserUtils {

  public static List<DataType> getCommonColumns(@NonNull String fileName,
      @NonNull List<String> rowCells) {
    ArrayList<DataType> commonColumns = new ArrayList<>();
    DataType rowDateTime = getRowDateTime(fileName, rowCells);
    commonColumns.add(rowDateTime);
    DataType batteryUsage = getBatteryUsage(rowCells);
    commonColumns.add(batteryUsage);
    DataType bluetoothStatus = getBluetoothStatus(rowCells);
    commonColumns.add(bluetoothStatus);
    DataType cpuFrequency = getCpuFrequency(rowCells);
    commonColumns.add(cpuFrequency);
    DataType cellSignal = getCellSignal(rowCells);
    commonColumns.add(cellSignal);
    DataType brightness = getBrightness(rowCells);
    commonColumns.add(brightness);
    DataType freeMemory = getFreeMemory(rowCells);
    commonColumns.add(freeMemory);
    DataType gpsStatus = getGpsStatus(rowCells);
    commonColumns.add(gpsStatus);
    DataType rowLocation = getRowLocation(rowCells);
    commonColumns.add(rowLocation);
    DataType wifiStatus = getWifiStatus(rowCells);
    commonColumns.add(wifiStatus);

    return commonColumns;
  }

  private static DataType getRowDateTime(@NonNull String fileName, @NonNull List<String> rowCells) {
    List<Integer> dateDdMmYy = Arrays.stream(
        fileName.substring(fileName.indexOf('_') + 1, fileName.indexOf('.')).split("-"))
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    List<Integer> timeHhMm = Arrays.stream(
        rowCells.get(0).split("\\."))
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    Collections.reverse(dateDdMmYy);
    List<Integer> rowDateTimeList = ListUtils.union(dateDdMmYy, timeHhMm);
    String rowDateTimeString = StringUtils.join(rowDateTimeList, COMMA);
    DataType rowDateTime = new RowDateTime();
    rowDateTime.setData(rowDateTimeString);
    return rowDateTime;
  }

  private static DataType getBatteryUsage(@NonNull List<String> rowCells) {
    DataType batteryUsage = new Battery();
    batteryUsage.setData(rowCells.get(1));
    return batteryUsage;
  }

  private static DataType getBluetoothStatus(@NonNull List<String> rowCells) {
    DataType bluetoothStatus = new BluetoothStatus();
    bluetoothStatus.setData(rowCells.get(2));
    return bluetoothStatus;
  }

  private static DataType getCpuFrequency(@NonNull List<String> rowCells) {
    DataType cpuFrequency = new CpuFrequency();
    cpuFrequency.setData(rowCells.get(3));
    return cpuFrequency;
  }

  private static DataType getCellSignal(@NonNull List<String> rowCells) {
    DataType cellSignal = new CellSignal();
    cellSignal.setData(rowCells.get(4));
    return cellSignal;
  }

  private static DataType getBrightness(@NonNull List<String> rowCells) {
    DataType brightness = new Brightness();
    brightness.setData(rowCells.get(5));
    return brightness;
  }

  private static DataType getFreeMemory(@NonNull List<String> rowCells) {
    DataType freeMemory = new FreeMemory();
    freeMemory.setData(rowCells.get(6));
    return freeMemory;
  }

  private static DataType getGpsStatus(@NonNull List<String> rowCells) {
    DataType gpsStatus = new GpsStatus();
    gpsStatus.setData(rowCells.get(7));
    return gpsStatus;
  }

  private static DataType getRowLocation(@NonNull List<String> rowCells) {
    DataType rowLocation = new RowLocation();
    if (rowCells.get(8).contains(PERCENT_LOC)) {
      return rowLocation;
    }
    rowLocation.setData(rowCells.get(8) + COMMA + rowCells.get(9));
    return rowLocation;
  }

  private static DataType getWifiStatus(@NonNull List<String> rowCells) {
    DataType gpsStatus = new WifiStatus();
    if (rowCells.get(8).contains(PERCENT_LOC)) {
      gpsStatus.setData(rowCells.get(9));
    } else {
      gpsStatus.setData(rowCells.get(10));
    }
    return gpsStatus;
  }
}
