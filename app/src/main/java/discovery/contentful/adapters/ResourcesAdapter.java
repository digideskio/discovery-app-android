package discovery.contentful.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import discovery.contentful.R;
import discovery.contentful.api.ResourceList;
import discovery.contentful.ui.FieldViewHolder;
import discovery.contentful.utils.Utils;
import com.contentful.java.cda.CDAContentType;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ResourcesAdapter extends BaseAdapter {
  private final Context context;
  private ResourceList data;

  public ResourcesAdapter(Context context) {
    this.context = context;
  }

  @Override public int getCount() {
    if (data == null) {
      return 0;
    }

    return data.resources.size();
  }

  @Override public CDAResource getItem(int position) {
    return data.resources.get(position);
  }

  @Override public long getItemId(int position) {
    return 0;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    FieldViewHolder vh;

    if (convertView == null) {
      convertView = LayoutInflater.from(context).inflate(R.layout.view_field, parent, false);

      convertView.setTag(vh = new FieldViewHolder(convertView));

      vh.tvValue.setVisibility(View.GONE);
      vh.ivArrow.setVisibility(View.VISIBLE);

      vh.tvTitle.setFont(false);
    } else {
      vh = (FieldViewHolder) convertView.getTag();
    }

    CDAResource resource = getItem(position);
    String name = null;

    if (resource instanceof CDAEntry) {
      String displayField = getEntryDisplayField((CDAEntry) resource);

      if (StringUtils.isNotBlank(displayField)) {
        name = ((CDAEntry) resource).getField(displayField);
      }
    }

    if (StringUtils.isBlank(name)) {
      name = resource.id();
    }

    vh.tvTitle.setText(name);

    return convertView;
  }

  private String getEntryDisplayField(CDAEntry entry) {
    return Utils.getContentTypeForEntry(data.contentTypes, entry).displayField();
  }

  public Map<String, CDAContentType> getContentTypesMap() {
    return data.contentTypes;
  }

  public void setResourceList(ResourceList resourceList) {
    this.data = resourceList;
  }

  public void clear() {
    data = null;
  }
}
